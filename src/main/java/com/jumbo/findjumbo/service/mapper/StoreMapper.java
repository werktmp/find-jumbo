package com.jumbo.findjumbo.service.mapper;

import com.jumbo.findjumbo.controller.dto.response.StoreDto;
import com.jumbo.findjumbo.domain.entity.Store;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreMapper {

    public StoreDto toDto(Store store) {
        return new StoreDto(
                store.city(),
                store.complexNumber()
        );
    }

    public List<StoreDto> toDtoList(List<Store> stores) {
        return stores.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
