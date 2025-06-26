package com.microservice.qualificacao.repository;

import com.microservice.qualificacao.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StateRepository extends MongoRepository<State, String> {
    State findByAcronym(String acronym);
}
