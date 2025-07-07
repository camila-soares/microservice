package com.microservice.fulfillments.repository;


import com.microservice.fulfillments.model.Fulfillment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface FulFillmentRepository  extends MongoRepository<Fulfillment, String> {
    Optional<Fulfillment> findByOrderId(String orderId);
}
