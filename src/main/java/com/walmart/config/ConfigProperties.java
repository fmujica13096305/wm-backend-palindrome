package com.walmart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "service.config")
public class ConfigProperties {

    private String documentImplementation;

    private String nameApplication;


    public String getDocumentImplementation() {
        return documentImplementation;
    }

    public void setDocumentImplementation(String documentImplementation) {
        this.documentImplementation = documentImplementation;
    }

    public String getNameApplication() {
        return nameApplication;
    }

    public void setNameApplication(String nameApplication) {
        this.nameApplication = nameApplication;
    }


}

