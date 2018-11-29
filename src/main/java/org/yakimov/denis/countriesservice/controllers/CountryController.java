package org.yakimov.denis.countriesservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.services.CountryService;
import org.yakimov.denis.countriesservice.zip.ZipDataExtractor;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ZipDataExtractor zipDataExtractor;

    @RequestMapping(method= RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Flux<CountryContent>> request(@RequestParam("file") MultipartFile file) {
        String zipName = file.getOriginalFilename();
        try {
            return new ResponseEntity<>(countryService.getContent(zipName, zipDataExtractor.getContent(file)), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
