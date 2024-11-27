package com.jumbo.findjumbo.service;

import com.jumbo.findjumbo.controller.dto.response.StoreDto;
import com.jumbo.findjumbo.domain.entity.Store;
import com.jumbo.findjumbo.domain.repository.StoreRepository;
import com.jumbo.findjumbo.service.mapper.StoreMapper;
import com.jumbo.findjumbo.util.GeoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StoreServiceTest {

    @MockBean
    private StoreRepository storeRepository;

    @MockBean
    private StoreMapper storeMapper;

    @Autowired
    private StoreService storeService;

    private Store store1, store2;
    private StoreDto storeDto1, storeDto2;

    @BeforeEach
    public void setUp() {
        store1 = new Store("Den Bosch", "10001", "5th Ave", "Suite 100", "Floor 10", "Main Store", "uuid-1", 40.7128, -74.0060, "123", true, "09:00", "17:00", "Retail", true, "SAP12345");
        store2 = new Store("Rosmalen", "90001", "Sunset Blvd", "Suite 200", "Floor 2", "Downtown Store", "uuid-2", 34.0522, -118.2437, "456", false, "10:00", "18:00", "Retail", false, "SAP67890");

        storeDto1 = new StoreDto("Den Bosch", "123");
        storeDto2 = new StoreDto("Rosmalen", "456");
    }

    @Test
    public void testShouldReturnAllStores() {
        // Given
        List<Store> stores = Arrays.asList(store1, store2);
        List<StoreDto> storesDto = Arrays.asList(storeDto1, storeDto2);

        when(storeRepository.findAll()).thenReturn(stores);
        when(storeMapper.toDtoList(stores)).thenReturn(storesDto);

        // When
        List<StoreDto> result = storeService.getStores();

        // Then
        assertEquals(2, result.size());
        assertEquals(storeDto1.city(), result.get(0).city());
        assertEquals(storeDto2.city(), result.get(1).city());
    }

    @Test
    public void testShouldReturnClosestStores() {
        // Given
        double latitude = 40.0;
        double longitude = -75.0;

        List<Store> stores = Arrays.asList(store1, store2);

        when(storeRepository.findAll()).thenReturn(stores);
        when(storeMapper.toDtoList(anyList())).thenReturn(Arrays.asList(storeDto1, storeDto2));

        try (MockedStatic<GeoUtils> geoUtilsMock = mockStatic(GeoUtils.class)) {
            geoUtilsMock.when(() -> GeoUtils.calculateDistance(latitude, longitude, store1.latitude(), store1.longitude()))
                    .thenReturn(10.0);
            geoUtilsMock.when(() -> GeoUtils.calculateDistance(latitude, longitude, store2.latitude(), store2.longitude()))
                    .thenReturn(5.0);

            // When
            List<StoreDto> result = storeService.getClosestStores(latitude, longitude);

            // Then
            assertEquals(2, result.size());
            assertEquals(storeDto1.city(), result.get(0).city());
            assertEquals(storeDto2.city(), result.get(1).city());
        }
    }
}