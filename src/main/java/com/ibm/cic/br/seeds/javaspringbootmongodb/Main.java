package com.ibm.cic.br.seeds.javaspringbootmongodb;

import com.ibm.cic.br.seeds.javaspringbootmongodb.dao.CustomerRepository;
import com.ibm.cic.br.seeds.javaspringbootmongodb.dao.models.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@RestController
@EnableAutoConfiguration
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    @Autowired
    private CustomerRepository customerRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {"application/json"})
    public List<Customer> findAll() {
        List<Customer> list = customerRepository.findAll();
        list.forEach(System.out::println);
        System.out.println(list.size());
        return list;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return new MongoCustomConversions(Arrays.asList(

                new Converter<LocalDate, String>() {
                    @Override
                    public String convert(@NonNull LocalDate source) {
                        return source.format(dateTimeFormatter);
                    }
                },

                new Converter<String, LocalDate>() {
                    @Override
                    public LocalDate convert(@NonNull String source) {
                        return LocalDate.parse(source, dateTimeFormatter);
                    }
                }

        ));
    }
}
