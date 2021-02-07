FROM  mongo:3.6.21-xenial

# This is in accordance to : https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-get-on-ubuntu-16-04
RUN apt-get update && \
	apt-get install -y openjdk-8-jdk && \
	apt-get install -y ant && \
	apt-get clean && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Fix certificate issues, found as of
# https://bugs.launchpad.net/ubuntu/+source/ca-certificates-java/+bug/983302
RUN apt-get update && \
	apt-get install -y ca-certificates-java && \
	apt-get clean && \
	update-ca-certificates -f && \
	rm -rf /var/lib/apt/lists/* && \
	rm -rf /var/cache/oracle-jdk8-installer;

# Setup JAVA_HOME, this is useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

# Install Curl
RUN apt-get update && \
	apt-get install curl -y

# Setup Supervisor
RUN apt-get -y install supervisor
RUN mkdir -p /var/log/supervisor

# Start the supervisor instance when the machine autostarts
RUN echo "supervisord -c /etc/supervisord.conf" >> /root/.bashrc

# Copy config file for Supervisor (Custom supervisord.conf)
COPY supervisord.conf /etc/supervisord.conf


RUN ls *

ARG MAVEN_VERSION=3.6.1
ARG USER_HOME_DIR="/root"
ARG BASE_URL=https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries

COPY ./pom.xml ./pom.xml
COPY ./src/main ./src/main

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"

ENV MONGO_SERVER=localhost
#ENV MONGO_PORT=27017
ENV MONGO_DATABASE=promotions
#ENV MONGO_INITDB_ROOT_USERNAME=productListUser
#ENV MONGO_INITDB_ROOT_PASSWORD=productListPassword

COPY ./database/products.json  /database/products.json
COPY ./entrypoint/products-entrypoint.sh /docker-entrypoint-initdb.d/products-entrypoint.sh
COPY ./java.env /java.env


# not sure why you need to `install` the package in the local repository?
RUN mvn -T 4 clean package -Dmaven.test.skip=true
RUN mkdir /opt/springboot-launcher
RUN cp -a /target/wps-backend-1.0.0-spring-boot.jar /opt/springboot-launcher/wps-backend-1.0.0.jar \
    && rm -rf "$HOME/.m2"

VOLUME /tmp
ADD springboot-launcher /opt/springboot-launcher
RUN chmod +x /opt/springboot-launcher/*.sh

COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

CMD /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf