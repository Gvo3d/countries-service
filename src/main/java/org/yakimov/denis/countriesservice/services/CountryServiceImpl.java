package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.http.HttpRequester;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.ZipArchive;
import org.yakimov.denis.countriesservice.repositories.CountryContentRepository;
import org.yakimov.denis.countriesservice.repositories.ZipArchiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryContentRepository repository;

    @Autowired
    private ZipArchiveRepository zipArchiveRepository;

    @Autowired
    private HttpRequester requester;

    @Override
    public Flux<CountryContent> getContent(String zipName, Map<String, List<String>> data) {
        ZipArchive archive = new ZipArchive();
        archive.setArchiveName(zipName);
        Mono<ZipArchive> zipMono = zipArchiveRepository.save(archive);
        archive = zipMono.block();

        List<RequestDto> contents = requester.processRequest(data.values().stream().flatMap(Collection::stream).collect(Collectors.toList()));

        for (RequestDto dto: contents){


        }

        List<CountryContent> preparedData = new ArrayList<>();
        for (RequestDto dto: contents){


        }


        return null;
    }


}
