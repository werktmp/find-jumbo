package com.jumbo.findjumbo.service;

import com.jumbo.findjumbo.controller.dto.response.StoreDto;
import com.jumbo.findjumbo.domain.entity.Store;
import com.jumbo.findjumbo.domain.repository.StoreRepository;
import com.jumbo.findjumbo.service.mapper.StoreMapper;
import com.jumbo.findjumbo.util.GeoUtils;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    public static final int DEFAULT_LIMIT = 5;

    public final StoreRepository storeRepository;
    public final StoreMapper storeMapper;

    public StoreService(StoreRepository storeRepository, StoreMapper storeMapper) {
        this.storeRepository = storeRepository;
        this.storeMapper = storeMapper;
    }

    public List<StoreDto> getStores() {
        List<Store> stores = this.storeRepository.findAll();
        return this.storeMapper.toDtoList(stores);
    }

    public List<StoreDto> getClosestStores(double latitude, double longitude) {
        return storeMapper.toDtoList(storeRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(store ->
                        GeoUtils.calculateDistance(latitude, longitude, store.latitude(), store.longitude())))
                .limit(DEFAULT_LIMIT)
                .collect(Collectors.toList()));
    }
}
