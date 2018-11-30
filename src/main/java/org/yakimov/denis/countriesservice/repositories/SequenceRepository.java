package org.yakimov.denis.countriesservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.yakimov.denis.countriesservice.models.Sequence;

@Repository
public interface SequenceRepository extends ReactiveMongoRepository<Sequence, String> {
}
