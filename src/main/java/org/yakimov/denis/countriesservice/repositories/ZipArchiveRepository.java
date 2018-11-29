package org.yakimov.denis.countriesservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.yakimov.denis.countriesservice.models.ZipArchive;

public interface ZipArchiveRepository extends ReactiveMongoRepository<ZipArchive, Long> {
}
