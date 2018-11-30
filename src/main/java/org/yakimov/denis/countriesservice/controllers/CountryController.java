package org.yakimov.denis.countriesservice.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.yakimov.denis.countriesservice.exceptions.EmptyFileException;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.Status;
import org.yakimov.denis.countriesservice.services.CountryService;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.zip.ZipDataExtractor;

import java.io.IOException;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private static final Logger LOGGER = Logger.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @Autowired
    private ZipDataExtractor zipDataExtractor;

    @RequestMapping(method= RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> request(@RequestParam("file") MultipartFile file) {
        String zipName = file.getOriginalFilename();
        LOGGER.info(String.format(Constants.PROCESSING, zipName));
        try {
            return new ResponseEntity<>(countryService.getContent(zipName, zipDataExtractor.getContent(file)), HttpStatus.OK);
        } catch (IOException e) {
            LOGGER.warn(Constants.UNKNOWN, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EmptyFileException e) {
            LOGGER.warn(Constants.EMPTY_FILE);
            CountryContent content = new CountryContent();
            content.setStatus(Status.NO_CONTENT);
            content.setMessage(Constants.EMPTY_FILE);
            return new ResponseEntity<>(content,HttpStatus.BAD_REQUEST);
        }
    }
}
