package com.jumbo.findjumbo.controller;

import com.jumbo.findjumbo.controller.dto.response.StoreDto;
import com.jumbo.findjumbo.service.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShouldReturnAllStores() throws Exception {
        // Given
        List<StoreDto> stores = Arrays.asList(
                new StoreDto("Den Bosch", "123"),
                new StoreDto("Rosmalen", "456")
        );

        // When
        when(storeService.getStores()).thenReturn(stores);

        // Then
        mockMvc.perform(get("/v1/stores")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Den Bosch"))
                .andExpect(jsonPath("$[0].complexNumber").value("123"))
                .andExpect(jsonPath("$[1].city").value("Rosmalen"))
                .andExpect(jsonPath("$[1].complexNumber").value("456"));
    }

    @Test
    void testShouldReturnClosestStores() throws Exception {
        List<StoreDto> stores = Arrays.asList(
                new StoreDto("Den Bosch", "123"),
                new StoreDto("Rosmalen", "456")
        );

        // When
        when(storeService.getClosestStores(51.697815, 5.303675)).thenReturn(stores);

        // Then
        mockMvc.perform(get("/v1/stores/closest?latitude=51.697815&longitude=5.303675")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].city").value("Den Bosch"))
                .andExpect(jsonPath("$[0].complexNumber").value("123"))
                .andExpect(jsonPath("$[1].city").value("Rosmalen"))
                .andExpect(jsonPath("$[1].complexNumber").value("456"));
    }
}