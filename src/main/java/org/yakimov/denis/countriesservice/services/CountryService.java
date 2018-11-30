package org.yakimov.denis.countriesservice.services;

import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CountryService {
    Flux<CountryContent> getContent(String fileName, List<FileDto> data) throws EmptyFileException;
}
