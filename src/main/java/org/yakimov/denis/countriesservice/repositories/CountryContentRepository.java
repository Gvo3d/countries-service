package org.yakimov.denis.countriesservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.yakimov.denis.countriesservice.models.CountryContent;

@Repository
public interface CountryContentRepository extends ReactiveMongoRepository<CountryContent, String> {
}
