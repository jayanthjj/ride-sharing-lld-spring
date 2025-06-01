package com.jayanth.rideshare.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RiderTest {

    @Test
    void constructor_shouldInitializeCorrectly() {
        // Given
        String name = "Jane Doe";
        Location location = new Location(10.0, 20.0);
        
        // When
        Rider rider = new Rider(name, location);
        
        // Then
        assertNotNull(rider.getId());
        assertEquals(name, rider.getName());
        assertEquals(location, rider.getCurrentLocation());
    }

    @Test
    void updateLocation_shouldUpdateRiderLocation() {
        // Given
        Location initialLocation = new Location(10.0, 20.0);
        Rider rider = new Rider("Jane Doe", initialLocation);
        assertEquals(initialLocation, rider.getCurrentLocation());
        
        // When
        Location newLocation = new Location(30.0, 40.0);
        rider.updateLocation(newLocation);
        
        // Then
        assertEquals(newLocation, rider.getCurrentLocation());
        assertNotEquals(initialLocation, rider.getCurrentLocation());
    }

    @Test
    void toString_shouldContainRiderInfo() {
        // Given
        String name = "Jane Doe";
        Location location = new Location(10.0, 20.0);
        Rider rider = new Rider(name, location);
        
        // When
        String riderString = rider.toString();
        
        // Then
        assertTrue(riderString.contains(name));
        assertTrue(riderString.contains(rider.getId()));
        assertTrue(riderString.contains(String.valueOf(location.getLatitude())));
        assertTrue(riderString.contains(String.valueOf(location.getLongitude())));
    }
}