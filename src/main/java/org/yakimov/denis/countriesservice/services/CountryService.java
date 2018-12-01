package org.yakimov.denis.countriesservice.services;

import org.springframework.http.codec.multipart.FilePart;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

public interface CountryService {
    void getContent(FilePart file, String sessionId) throws EmptyFileException, IOException;
}
