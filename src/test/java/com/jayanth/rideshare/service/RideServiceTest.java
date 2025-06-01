package com.jayanth.rideshare.service;

import com.jayanth.rideshare.model.*;
import com.jayanth.rideshare.observer.NotificationService;
import com.jayanth.rideshare.strategy.FareStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RideServiceTest {

    @Mock
    private DriverService driverService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private FareStrategy fareStrategy;

    private RideService rideService;
    private Driver driver;
    private Rider rider;
    private Location pickupLocation;
    private Location dropLocation;
    private String rideId;

    @BeforeEach
    void setUp() {
        // Initialize the service with mocked dependencies
        rideService = new RideService(driverService, notificationService);
        
        // Setup test data
        pickupLocation = new Location(12.9716, 77.5946); // Bangalore coordinates
        dropLocation = new Location(13.0827, 80.2707); // Chennai coordinates
        driver = new Driver("John Doe", pickupLocation);
        rider = new Rider("Jane Doe", pickupLocation);
        rideId = UUID.randomUUID().toString();
    }

    @Test
    void createRide_whenDriverAvailable_shouldReturnRide() {
        // Given
        when(driverService.findNearestAvailableDriver(any(Location.class))).thenReturn(driver);
        
        // When
        Ride ride = rideService.createRide(rider, dropLocation);
        
        // Then
        assertNotNull(ride);
        assertEquals(rider, ride.getRider());
        assertEquals(driver, ride.getDriver());
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
        assertFalse(driver.isAvailable());
        verify(notificationService).notifyAllObservers(eq(driver), anyString());
        
        // Verify ride is stored in history
        Map<String, Ride> rideHistory = rideService.getRideHistory();
        assertEquals(1, rideHistory.size());
        assertTrue(rideHistory.containsKey(ride.getId()));
    }

    @Test
    void createRide_whenNoDriverAvailable_shouldReturnNull() {
        // Given
        when(driverService.findNearestAvailableDriver(any(Location.class))).thenReturn(null);
        
        // When
        Ride ride = rideService.createRide(rider, dropLocation);
        
        // Then
        assertNull(ride);
        verify(notificationService, never()).notifyAllObservers(any(), anyString());
        
        // Verify no ride stored in history
        Map<String, Ride> rideHistory = rideService.getRideHistory();
        assertTrue(rideHistory.isEmpty());
    }

    @Test
    void completeRide_whenRideExists_shouldUpdateRideStatusAndMakeDriverAvailable() {
        // Given
        when(driverService.findNearestAvailableDriver(any(Location.class))).thenReturn(driver);
        Ride ride = rideService.createRide(rider, dropLocation);
        String createdRideId = ride.getId();
        
        // When
        rideService.completeRide(createdRideId, fareStrategy);
        
        // Then
        verify(fareStrategy, never()).calculateFare(any(), any()); // We're not directly testing fare calculation
        assertTrue(driver.isAvailable());
        assertEquals(RideStatus.COMPLETED, ride.getStatus());
    }

    @Test
    void completeRide_whenRideDoesNotExist_shouldDoNothing() {
        // Given a non-existent ride ID
        String nonExistentRideId = "non-existent-id";
        
        // When
        rideService.completeRide(nonExistentRideId, fareStrategy);
        
        // Then - no exceptions should be thrown
        verify(fareStrategy, never()).calculateFare(any(), any());
    }

    @Test
    void getRideHistory_shouldReturnAllRides() {
        // Given
        when(driverService.findNearestAvailableDriver(any(Location.class))).thenReturn(driver);
        Ride ride1 = rideService.createRide(rider, dropLocation);
        Ride ride2 = rideService.createRide(rider, new Location(22.5726, 88.3639)); // Kolkata coordinates
        
        // When
        Map<String, Ride> rideHistory = rideService.getRideHistory();
        
        // Then
        assertEquals(2, rideHistory.size());
        assertTrue(rideHistory.containsKey(ride1.getId()));
        assertTrue(rideHistory.containsKey(ride2.getId()));
    }
}