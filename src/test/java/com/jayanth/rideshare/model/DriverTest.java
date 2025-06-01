package com.jayanth.rideshare.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    @Test
    void constructor_shouldInitializeCorrectly() {
        // Given
        String name = "John Doe";
        Location location = new Location(10.0, 20.0);
        
        // When
        Driver driver = new Driver(name, location);
        
        // Then
        assertNotNull(driver.getId());
        assertEquals(name, driver.getName());
        assertEquals(location, driver.getCurrentLocation());
        assertTrue(driver.isAvailable());
    }

    @Test
    void setAvailable_shouldUpdateAvailabilityStatus() {
        // Given
        Driver driver = new Driver("John Doe", new Location(10.0, 20.0));
        assertTrue(driver.isAvailable());
        
        // When
        driver.setAvailable(false);
        
        // Then
        assertFalse(driver.isAvailable());
        
        // When
        driver.setAvailable(true);
        
        // Then
        assertTrue(driver.isAvailable());
    }

    @Test
    void updateLocation_shouldUpdateDriverLocation() {
        // Given
        Location initialLocation = new Location(10.0, 20.0);
        Driver driver = new Driver("John Doe", initialLocation);
        assertEquals(initialLocation, driver.getCurrentLocation());
        
        // When
        Location newLocation = new Location(30.0, 40.0);
        driver.updateLocation(newLocation);
        
        // Then
        assertEquals(newLocation, driver.getCurrentLocation());
        assertNotEquals(initialLocation, driver.getCurrentLocation());
    }

    @Test
    void toString_shouldContainDriverInfo() {
        // Given
        String name = "John Doe";
        Location location = new Location(10.0, 20.0);
        Driver driver = new Driver(name, location);
        
        // When
        String driverString = driver.toString();
        
        // Then
        assertTrue(driverString.contains(name));
        assertTrue(driverString.contains(driver.getId()));
        assertTrue(driverString.contains(String.valueOf(location.getLatitude())));
        assertTrue(driverString.contains(String.valueOf(location.getLongitude())));
        assertTrue(driverString.contains("available=true"));
    }
}