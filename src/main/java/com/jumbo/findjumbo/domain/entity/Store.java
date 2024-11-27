package com.jumbo.findjumbo.domain.entity;

public record Store(
        String city,
        String postalCode,
        String street,
        String street2,
        String street3,
        String addressName,
        String uuid,
        double longitude,
        double latitude,
        String complexNumber,
        boolean showWarningMessage,
        String todayOpen,
        String todayClose,
        String locationType,
        boolean collectionPoint,
        String sapStoreID) {
}
