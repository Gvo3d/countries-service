package org.yakimov.denis.countriesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;

//@SpringBootApplication(exclude = {MongoReactiveDataAutoConfiguration.class}, scanBasePackages = {"org.yakimov.denis.countriesservice"})
@SpringBootApplication()
public class CountriesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountriesServiceApplication.class, args);
    }
}
