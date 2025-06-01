package com.jayanth.rideshare.service;

import com.jayanth.rideshare.model.Driver;
import com.jayanth.rideshare.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverServiceTest {

    private DriverService driverService;
    private Location bengaluruLocation;
    private Location mumbaiLocation;
    private Location delhiLocation;
    private Driver driver1;
    private Driver driver2;
    private Driver driver3;

    @BeforeEach
    void setUp() {
        driverService = new DriverService();
        
        // Setup test locations
        bengaluruLocation = new Location(12.9716, 77.5946);
        mumbaiLocation = new Location(19.0760, 72.8777);
        delhiLocation = new Location(28.7041, 77.1025);
        
        // Setup test drivers
        driver1 = new Driver("Driver 1", bengaluruLocation);
        driver2 = new Driver("Driver 2", mumbaiLocation);
        driver3 = new Driver("Driver 3", delhiLocation);
    }

    @Test
    void registerDriver_shouldAddDriverToList() {
        // When
        driverService.registerDriver(driver1);
        
        // Then
        Driver foundDriver = driverService.findNearestAvailableDriver(bengaluruLocation);
        assertNotNull(foundDriver);
        assertEquals("Driver 1", foundDriver.getName());
    }

    @Test
    void findNearestAvailableDriver_whenMultipleDriversAvailable_shouldReturnNearest() {
        // Given
        driverService.registerDriver(driver1);  // Bengaluru
        driverService.registerDriver(driver2);  // Mumbai
        driverService.registerDriver(driver3);  // Delhi
        
        // Test Location: Chennai (closer to Bengaluru than Mumbai or Delhi)
        Location chennaiLocation = new Location(13.0827, 80.2707);
        
        // When
        Driver nearestDriver = driverService.findNearestAvailableDriver(chennaiLocation);
        
        // Then
        assertNotNull(nearestDriver);
        assertEquals("Driver 1", nearestDriver.getName());
    }

    @Test
    void findNearestAvailableDriver_whenNoDriversAvailable_shouldReturnNull() {
        // Given no drivers registered
        
        // When
        Driver nearestDriver = driverService.findNearestAvailableDriver(bengaluruLocation);
        
        // Then
        assertNull(nearestDriver);
    }

    @Test
    void findNearestAvailableDriver_whenDriversNotAvailable_shouldReturnNull() {
        // Given
        driverService.registerDriver(driver1);  // Bengaluru
        driver1.setAvailable(false);
        
        // When
        Driver nearestDriver = driverService.findNearestAvailableDriver(bengaluruLocation);
        
        // Then
        assertNull(nearestDriver);
    }

    @Test
    void findNearestAvailableDriver_whenSomeDriversNotAvailable_shouldIgnoreThem() {
        // Given
        driverService.registerDriver(driver1);  // Bengaluru
        driverService.registerDriver(driver2);  // Mumbai
        driver1.setAvailable(false);  // Make the nearest driver unavailable
        
        // When
        Driver nearestDriver = driverService.findNearestAvailableDriver(bengaluruLocation);
        
        // Then
        assertNotNull(nearestDriver);
        assertEquals("Driver 2", nearestDriver.getName());
    }
}