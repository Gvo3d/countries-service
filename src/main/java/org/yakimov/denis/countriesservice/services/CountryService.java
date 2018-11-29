package org.yakimov.denis.countriesservice.services;

import org.yakimov.denis.countriesservice.models.CountryContent;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

public interface CountryService {
    Flux<CountryContent> getContent(String fileName, Map<String, List<String>> data);
}
