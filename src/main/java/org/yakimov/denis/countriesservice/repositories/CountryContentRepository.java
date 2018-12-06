package org.yakimov.denis.countriesservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.yakimov.denis.countriesservice.models.CountryContent;
import reactor.core.publisher.Flux;

@Repository
public interface CountryContentRepository extends ReactiveMongoRepository<CountryContent, String> {
    Flux<CountryContent> findAllByCountryCode(String code);
}
