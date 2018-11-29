package org.yakimov.denis.countriesservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.services.CountryService;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebConfiguration {

    @Autowired
    private CountryService countryService;

//    @Bean
//    RouterFunction<ServerResponse> getAllEmployeesRoute(@RequestParam("file") MultipartFile file) {
//        String[] data = null;
//        return route(POST("/employees"),
//                req -> ok().body(
//                        countryService.getContent(data), CountryContent.class));
//    }
}
