package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.http.HttpRequester;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.FileData;
import org.yakimov.denis.countriesservice.models.ZipArchive;
import org.yakimov.denis.countriesservice.repositories.CountryContentRepository;
import org.yakimov.denis.countriesservice.repositories.ZipArchiveRepository;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.support.DataProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.*;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryContentRepository repository;

    @Autowired
    private ZipArchiveRepository zipArchiveRepository;

    @Autowired
    private HttpRequester requester;

    @Autowired
    private SequenceService sequenceService;

    @Override
    public Flux<CountryContent> getContent(String zipName, List<FileData> data) throws EmptyFileException {

        Map<RequestDto, FileData> requestResults = requester.processRequest(data);
        List<CountryContent> resultList = DataProcessor.generateCountryContent(requestResults);

        if (resultList==null || resultList.isEmpty()){
            throw new EmptyFileException();
        }

//        ZipArchive archive = resultList.get(0).getFileData().getArchive();
//        archive.setId(sequenceService.getNextSequence(Constants.SEQUENCE_ZIP));
//        Mono<ZipArchive> zipMono = zipArchiveRepository.save(archive);
//        archive = zipMono.block();

        //resultList.forEach(x->x.setId(sequenceService.getNextSequence(Constants.SEQUENCE_COUNTRY)));
        return repository.saveAll(resultList).subscribe();
    }


}
