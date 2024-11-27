package com.jumbo.findjumbo.util;

public class GeoUtils {

    private static final double EARTH_RADIUS_KM = 6371.0;

    /**
     * Calculates the distance between two geographical points using the Haversine formula.
     *
     * @param latitude1  The latitude of the first point in degrees.
     * @param longitude1 The longitude of the first point in degrees.
     * @param latitude2  The latitude of the second point in degrees.
     * @param longitude2 The longitude of the second point in degrees.
     * @return The distance between the two points in kilometers.
     */
    public static double calculateDistance(double latitude1, double longitude1, double latitude2, double longitude2) {
        double latDistance = Math.toRadians(latitude2 - latitude1);
        double lonDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }
}
