package com.jumbo.findjumbo.controller;

import com.jumbo.findjumbo.controller.dto.response.StoreDto;
import com.jumbo.findjumbo.service.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public ResponseEntity<List<StoreDto>> getStores() {
        return new ResponseEntity<>(this.storeService.getStores(), HttpStatus.OK);
    }

    @GetMapping("/closest")
    public ResponseEntity<List<StoreDto>> getClosest(@RequestParam double latitude, @RequestParam double longitude) {
        return new ResponseEntity<>(this.storeService.getClosestStores(latitude, longitude), HttpStatus.OK);
    }
}
