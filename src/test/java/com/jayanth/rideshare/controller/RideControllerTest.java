package com.jayanth.rideshare.controller;

import com.jayanth.rideshare.model.*;
import com.jayanth.rideshare.service.*;
import com.jayanth.rideshare.strategy.SimpleFareStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RideControllerTest {

    @Mock
    private RideService rideService;

    @Mock
    private DriverService driverService;

    @InjectMocks
    private RideController rideController;

    private Driver driver;
    private Rider rider;
    private Location pickupLocation;
    private Location dropLocation;
    private Ride ride;
    private String rideId;

    @BeforeEach
    void setUp() {
        // Setup test data
        pickupLocation = new Location(12.9716, 77.5946); // Bangalore coordinates
        dropLocation = new Location(13.0827, 80.2707); // Chennai coordinates
        driver = new Driver("John Doe", pickupLocation);
        rider = new Rider("Jane Doe", pickupLocation);
        rideId = UUID.randomUUID().toString();
        ride = new Ride(rideId, rider, driver, pickupLocation, dropLocation);
    }

    @Test
    void registerDriver_shouldReturnSuccessMessage() {
        // When
        String result = rideController.registerDriver(driver);
        
        // Then
        verify(driverService).registerDriver(driver);
        assertEquals("Driver registered: John Doe", result);
    }

    @Test
    void bookRide_whenDriverAvailable_shouldReturnSuccessMessage() {
        // Given
        when(rideService.createRide(eq(rider), any(Location.class))).thenReturn(ride);
        
        // When
        String result = rideController.bookRide(rider, dropLocation.getLatitude(), dropLocation.getLongitude());
        
        // Then
        verify(rideService).createRide(eq(rider), any(Location.class));
        assertTrue(result.contains("Ride booked with driver: John Doe"));
        assertTrue(result.contains("Ride ID: " + ride.getId()));
    }

    @Test
    void bookRide_whenNoDriverAvailable_shouldReturnNoDriversMessage() {
        // Given
        when(rideService.createRide(eq(rider), any(Location.class))).thenReturn(null);
        
        // When
        String result = rideController.bookRide(rider, dropLocation.getLatitude(), dropLocation.getLongitude());
        
        // Then
        assertEquals("No drivers available", result);
    }

    @Test
    void completeRide_shouldReturnSuccessMessage() {
        // When
        String result = rideController.completeRide(rideId);
        
        // Then
        verify(rideService).completeRide(eq(rideId), any(SimpleFareStrategy.class));
        assertEquals("Ride " + rideId + " completed.", result);
    }

    @Test
    void getAllRides_shouldReturnRideCollection() {
        // Given
        Map<String, Ride> rideMap = new HashMap<>();
        rideMap.put(rideId, ride);
        when(rideService.getRideHistory()).thenReturn(rideMap);
        
        // When
        Collection<Ride> result = rideController.getAllRides();
        
        // Then
        assertEquals(1, result.size());
        assertTrue(result.contains(ride));
    }
}