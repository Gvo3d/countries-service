package org.yakimov.denis.countriesservice.services;

import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.FileData;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

public interface CountryService {
    Flux<CountryContent> getContent(String fileName, List<FileData> data) throws EmptyFileException;
}
