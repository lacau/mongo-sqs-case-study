package com.player.poc.playerpoc.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import java.util.Arrays;
import java.util.Collection;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${db.mongo.name}")
    private String DB_NAME;

    @Value("${db.mongo.addr}")
    private String DB_ADDR;

    @Value("${db.mongo.port}")
    private Integer DB_PORT;

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(DB_ADDR, DB_PORT);
    }

    @Override
    protected Collection<String> getMappingBasePackages() {
        return Arrays.asList("com.player.poc");
    }
}
