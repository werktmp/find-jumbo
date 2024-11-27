package com.jumbo.findjumbo.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoUtilsTest {

    @Test
    public void testCalculateDistance() {
        // Given
        double latitude1 = 40.7128;
        double longitude1 = -74.0060;
        double latitude2 = 34.0522;
        double longitude2 = -118.2437;

        double expectedDistance = 3935.75;

        // When
        double actualDistance = GeoUtils.calculateDistance(latitude1, longitude1, latitude2, longitude2);

        // Then
        assertEquals(expectedDistance, actualDistance, 0.5);
    }
}
