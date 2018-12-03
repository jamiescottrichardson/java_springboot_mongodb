package com.ibm.cic.br.seeds.javaspringbootmongodb.config;

import com.ibm.cic.br.seeds.javaspringbootmongodb.converters.StringToDateTimeConverter;
import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.ibm.cic.br.seeds.javaspringbootmongodb.dao.*")
@ComponentScan(basePackages = "com.ibm")
public class SpringMongoConfig extends AbstractMongoConfiguration {

    private static final Logger logger = LogManager.getLogger(SpringMongoConfig.class.getName());
    private List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Bean
    public MongoClient mongoClient() {
        return new MongoClient(host, port);
    }

    @Override
    public String getMappingBasePackage() {
        return "com.ibm";
    }

    @Override
    public MongoCustomConversions customConversions() {
        converters.add(new StringToDateTimeConverter());
        return new MongoCustomConversions(converters);
    }
}

