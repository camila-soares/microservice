package com.microservice.qualificacao.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.qualificacao.model.State;
import com.microservice.qualificacao.repository.StateRepository;
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

@Component
@RequiredArgsConstructor
@Slf4j
public class StateCollectionInitialization {

    @Qualifier("gridFsTemplate")
    private final ResourceLoader resourceLoader;
    private final StateRepository repository;
    private final ObjectMapper objectMapper;

    @EventListener
    public void onApplicationEvent(@NotNull  ContextRefreshedEvent event) {
        try {
            if (repository.count() > 0)
                return;

            String json = resourceLoader.getResource("classpath:data/state.json")
                    .getContentAsString(StandardCharsets.UTF_8);
            List<State> states = objectMapper.readValue(json, new TypeReference<List<State>>() {});
            log.info("Populating databases... " + states);
            repository.saveAll(states);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
