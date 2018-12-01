package org.yakimov.denis.countriesservice.configuration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.yakimov.denis.countriesservice.dtos.IdentityDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.Status;
import org.yakimov.denis.countriesservice.services.CountryService;
import org.yakimov.denis.countriesservice.support.Constants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class WebConfiguration {
    private static final Logger LOGGER = Logger.getLogger(WebConfiguration.class);

    @Autowired
    private CountryService countryService;

    @Bean
    RouterFunction<ServerResponse> makeAllRoutes() {
        //return request.body(BodyExtractors.toMultipartData()).
        return route(POST(Constants.URL)
                        .and(accept(MediaType.MULTIPART_FORM_DATA))
                        .and(queryParam("file", Objects::nonNull)),
                req -> req.body(BodyExtractors.toMultipartData()).flatMap(x -> {
                    FilePart file = (FilePart) x.toSingleValueMap().get(Constants.FILE_PARAM);
                    Flux<CountryContent> result = null;
                    try {
                        result = countryService.getContent(file, req.pathVariable(Constants.SESSION_MARKER));
                    } catch (EmptyFileException e) {
                        LOGGER.warn(e);
                        return ServerResponse.status(HttpStatus.NO_CONTENT).body(BodyInserters.fromObject(Status.NO_CONTENT));
                    } catch (IOException e) {
                        LOGGER.warn(e);
                        return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BodyInserters.fromObject(Status.ERROR));
                    }
                    return ServerResponse.status(HttpStatus.PROCESSING).body(BodyInserters.fromObject(result));
                }));
    }

    @Bean
    RouterFunction<ServerResponse> getSessionId() {
        return route(GET("/session"),
                req -> req.session().flatMap(x-> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromObject(new IdentityDto(x.getId())))));
    }
}
