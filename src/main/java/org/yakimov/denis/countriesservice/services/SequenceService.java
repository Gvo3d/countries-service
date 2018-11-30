package org.yakimov.denis.countriesservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yakimov.denis.countriesservice.models.Sequence;
import org.yakimov.denis.countriesservice.repositories.SequenceRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceService {
//    @Autowired
//    private MongoOperations mongo;

    @Autowired
    private SequenceRepository sequenceRepository;

    @Transactional
    long getNextSequence(String name) {
//        Sequence counter = mongo.findAndModify(
//                query(where("_id").is(name)),
//                new Update().inc("seq", 1),
//                options().returnNew(true),
//                Sequence.class);
        System.out.println("Searching: "+name);
        Mono<Sequence> mono = sequenceRepository.findById(name);
        Flux<Sequence> sequences = sequenceRepository.findAll();
        System.out.println(sequences.toString());
        Sequence sequence = mono.block();
        System.out.println("sequence: "+sequence);
        long result = sequence.getSeq();
        sequence.setSeq(++result);
        sequenceRepository.save(sequence);
        return result;
    }
}
