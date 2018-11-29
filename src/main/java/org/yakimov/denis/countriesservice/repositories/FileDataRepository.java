package org.yakimov.denis.countriesservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.yakimov.denis.countriesservice.models.CountryContent;
import org.yakimov.denis.countriesservice.models.FileData;
import reactor.core.publisher.Flux;

@Repository
public interface FileDataRepository extends ReactiveMongoRepository<FileData, Long> {
}
