package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.http.HttpRequester;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.repositories.CountryContentRepository;
import org.yakimov.denis.countriesservice.support.DataProcessor;
import org.yakimov.denis.countriesservice.zip.ZipDataExtractor;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryContentRepository repository;

    @Autowired
    private ZipDataExtractor zipDataExtractor;

    @Autowired
    private HttpRequester requester;

    @Override
    public void getContent(FilePart file, String sessionId) throws EmptyFileException, IOException {
        List<FileDto> data = zipDataExtractor.getContent(file);

        Map<RequestDto, FileDto> requestResults = requester.processRequest(data);
        List<CountryContent> resultList = DataProcessor.generateCountryContent(requestResults);

        if (resultList==null || resultList.isEmpty()){
            throw new EmptyFileException();
        }
        return repository.saveAll(resultList);
    }
}