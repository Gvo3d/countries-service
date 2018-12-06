package org.yakimov.denis.countriesservice.services;

import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import reactor.core.publisher.Flux;

import java.io.IOException;

public interface CountryService {
    void getContent(MultipartFile file, String sessionId) throws EmptyFileException, IOException;
    Flux<CountryContent> getLastRequests(String code);
}
