package com.jayanth.rideshare.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    @Test
    void constructor_shouldInitializeCorrectly() {
        // When
        Location location = new Location(10.0, 20.0);
        
        // Then
        assertEquals(10.0, location.getLatitude());
        assertEquals(20.0, location.getLongitude());
    }

    @Test
    void distanceTo_shouldCalculateCorrectDistanceBetweenTwoPoints() {
        // Given
        Location location1 = new Location(0.0, 0.0);
        Location location2 = new Location(3.0, 4.0);
        
        // When
        double distance = location1.distanceTo(location2);
        
        // Then
        assertEquals(5.0, distance, 0.0001); // Using Euclidean distance: sqrt(3^2 + 4^2) = 5
    }

    @Test
    void distanceTo_whenSameLocation_shouldReturnZero() {
        // Given
        Location location = new Location(10.0, 20.0);
        
        // When
        double distance = location.distanceTo(location);
        
        // Then
        assertEquals(0.0, distance, 0.0001);
    }
}