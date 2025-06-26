package com.microservice.invetoryms.repository;

import com.microservice.invetoryms.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
