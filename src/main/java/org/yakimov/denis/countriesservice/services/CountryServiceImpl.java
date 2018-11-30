package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.http.HttpRequester;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.repositories.CountryContentRepository;
import org.yakimov.denis.countriesservice.support.DataProcessor;
import reactor.core.publisher.Flux;

import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryContentRepository repository;

    @Autowired
    private HttpRequester requester;

    @Override
    public Flux<CountryContent> getContent(String zipName, List<FileDto> data) throws EmptyFileException {

        Map<RequestDto, FileDto> requestResults = requester.processRequest(data);
        List<CountryContent> resultList = DataProcessor.generateCountryContent(requestResults);

        if (resultList==null || resultList.isEmpty()){
            throw new EmptyFileException();
        }
        return repository.saveAll(resultList);
    }
}