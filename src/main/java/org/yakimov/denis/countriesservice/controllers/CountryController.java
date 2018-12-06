package org.yakimov.denis.countriesservice.controllers;

import com.fasterxml.jackson.annotation.JsonView;
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
import org.yakimov.denis.countriesservice.support.JacksonView;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/countries")
public class CountryController {
    private static final Logger LOGGER = Logger.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    @CrossOrigin("*")
    @JsonView(JacksonView.Normal.class)
    @RequestMapping(value = "/{session}", method= RequestMethod.POST,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Status> request(@RequestParam(value="file", required = true) MultipartFile file, @PathVariable("session") String session) {
        String zipName = file.getOriginalFilename();
        LOGGER.info(String.format(Constants.PROCESSING, zipName));

        try {
            countryService.getContent(file, session);
            return new ResponseEntity<>(Status.SUCCESS, HttpStatus.OK);
        } catch (IOException e) {
            LOGGER.warn(Constants.UNKNOWN, e);
            return new ResponseEntity<>(Status.ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EmptyFileException e) {
            LOGGER.warn(Constants.EMPTY_FILE);
            return new ResponseEntity<>(Status.NO_CONTENT,HttpStatus.BAD_REQUEST);
        }
    }


    @CrossOrigin("*")
    @JsonView(JacksonView.Full.class)
    @RequestMapping(value = "/{code}",method= RequestMethod.GET,
            produces={MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Flux<CountryContent>> request(@PathVariable("code") String code) {
        return new ResponseEntity<>(countryService.getLastRequests(code), HttpStatus.OK);
    }
}
