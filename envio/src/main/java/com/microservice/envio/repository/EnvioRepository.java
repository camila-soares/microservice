package com.microservice.envio.repository;

import com.microservice.envio.model.Envio;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnvioRepository extends MongoRepository<Envio, String> {
}
