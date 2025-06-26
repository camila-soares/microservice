package com.microservice.invetoryms.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.invetoryms.model.Inventory;
import com.microservice.invetoryms.repository.InventoryRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryCollectionInitialization {

    @Qualifier("gridFsTemplate")
    private final ResourceLoader resourceLoader;

    private final InventoryRepository repository;

    private final ObjectMapper objectMapper;

    @EventListener
    public void onApplicationEvent(@NotNull ContextRefreshedEvent event) {
        try {
            if (repository.count() > 0)
                return;

            String json = resourceLoader.getResource("classpath:data/inventory.json")
                    .getContentAsString(StandardCharsets.UTF_8);

            List<Inventory> inventories = objectMapper.readValue(json, new TypeReference<>() {
            });

            log.info("Populando database... " + inventories);

            repository.saveAll(inventories);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}