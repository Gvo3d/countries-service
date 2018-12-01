package org.yakimov.denis.countriesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

//@SpringBootApplication(exclude = {MongoReactiveDataAutoConfiguration.class}, scanBasePackages = {"org.yakimov.denis.countriesservice"})
@SpringBootApplication()
@EnableWebFlux
public class CountriesServiceApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CountriesServiceApplication.class);
        application.setWebApplicationType(WebApplicationType.REACTIVE);
        application.run(CountriesServiceApplication.class, args);
        //SpringApplication.run(CountriesServiceApplication.class, args);
    }
}
