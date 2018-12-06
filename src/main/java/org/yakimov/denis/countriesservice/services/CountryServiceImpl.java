package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.http.HttpRequester;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.repositories.CountryContentRepository;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.support.DataProcessor;
import org.yakimov.denis.countriesservice.zip.ZipDataExtractor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {
    private static Sort SORT = new Sort(Sort.Direction.DESC, "requestDate");

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private CountryContentRepository repository;

    @Autowired
    private ZipDataExtractor zipDataExtractor;

    @Autowired
    private HttpRequester requester;


    @Override
    public void getContent(MultipartFile file, String sessionId) throws EmptyFileException, IOException {
        List<FileDto> data = zipDataExtractor.getContent(file);

        Map<RequestDto, FileDto> requestResults = requester.processRequest(data);
        List<CountryContent> resultList = DataProcessor.generateCountryContent(requestResults);

        if (resultList==null || resultList.isEmpty()){
            throw new EmptyFileException();
        }
        for (CountryContent content: resultList) {
            Mono<CountryContent> mono = repository.save(content);
            CountryContent result = mono.block();
            messagingTemplate.convertAndSendToUser(sessionId, Constants.SOCKET_RESPONSE_URL, result, createHeaders(sessionId));
        }
    }

    @Override
    public Flux<CountryContent> getLastRequests(String code) {
        return repository.findAllByCountryCode(code);
    }


    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}