package com.jumbo.findjumbo.domain.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jumbo.findjumbo.domain.entity.Store;
import com.jumbo.findjumbo.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Repository
public class StoreRepository {

    private static final Logger logger = LoggerFactory.getLogger(StoreService.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Store> loadStoresFromJson() {
        try (InputStream inputStream = getClass().getResourceAsStream("/stores.json")) {
            if (inputStream == null) {
                logger.warn("Stores JSON file not found in resources folder.");
                throw new RuntimeException("Stores JSON file not found.");
            }
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });

        } catch (IOException e) {
            logger.error("Failed to load store data from JSON file", e);
            throw new RuntimeException("Failed to load store data from JSON file", e);
        }
    }

    public List<Store> findAll() {
        return this.loadStoresFromJson();
    }
}
