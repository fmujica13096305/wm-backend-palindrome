package com.walmart.integrationTest;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

import java.io.IOException;

public class EmbeddedMongo {
    static MongodExecutable mongodExecutable;
    private int port;

    private MongodProcess process;

    public EmbeddedMongo() {
        this.port = 27017;
    }

    public EmbeddedMongo(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        String bindIp = "localhost";
        int port = 12345;
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(bindIp, port, Network.localhostIsIPv6()))
                .build();
        mongodExecutable = null;
        try {
            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();
        } catch (Exception e) {
            // log exception here
            if (mongodExecutable != null)
                mongodExecutable.stop();
        }
    }

    public void stop() {
        process.stop();
    }

}
