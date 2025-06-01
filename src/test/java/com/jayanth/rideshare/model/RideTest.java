package com.jayanth.rideshare.model;

import com.jayanth.rideshare.strategy.FareStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RideTest {

    private Ride ride;
    private Driver driver;
    private Rider rider;
    private Location pickupLocation;
    private Location dropLocation;
    private String rideId;

    @Mock
    private FareStrategy fareStrategy;

    @BeforeEach
    void setUp() {
        rideId = UUID.randomUUID().toString();
        pickupLocation = new Location(12.9716, 77.5946); // Bangalore
        dropLocation = new Location(13.0827, 80.2707); // Chennai
        rider = new Rider("Jane Doe", pickupLocation);
        driver = new Driver("John Doe", pickupLocation);
        ride = new Ride(rideId, rider, driver, pickupLocation, dropLocation);
    }

    @Test
    void constructor_shouldInitializeCorrectly() {
        // Then
        assertEquals(rideId, ride.getId());
        assertEquals(rider, ride.getRider());
        assertEquals(driver, ride.getDriver());
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
        assertEquals(0.0, ride.getFare());
    }

    @Test
    void startRide_shouldChangeStatusToInProgress() {
        // When
        ride.startRide();
        
        // Then
        assertEquals(RideStatus.IN_PROGRESS, ride.getStatus());
    }

    @Test
    void startRide_whenNotInRequestedState_shouldNotChangeStatus() {
        // Given
        ride.startRide(); // Now in IN_PROGRESS
        assertEquals(RideStatus.IN_PROGRESS, ride.getStatus());
        
        // When trying to start again
        ride.startRide();
        
        // Then - still in IN_PROGRESS, not changed
        assertEquals(RideStatus.IN_PROGRESS, ride.getStatus());
    }

    @Test
    void endRide_shouldChangeStatusToCompletedAndCalculateFare() {
        // Given
        when(fareStrategy.calculateFare(pickupLocation, dropLocation)).thenReturn(150.0);
        ride.startRide();
        
        // When
        ride.endRide(fareStrategy);
        
        // Then
        assertEquals(RideStatus.COMPLETED, ride.getStatus());
        assertEquals(150.0, ride.getFare());
        verify(fareStrategy).calculateFare(pickupLocation, dropLocation);
    }

    @Test
    void endRide_whenNotInProgress_shouldNotChangeStatusOrCalculateFare() {
        // Given - ride is still in REQUESTED state
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
        
        // When
        ride.endRide(fareStrategy);
        
        // Then
        assertEquals(RideStatus.REQUESTED, ride.getStatus());
        assertEquals(0.0, ride.getFare());
        verify(fareStrategy, never()).calculateFare(any(), any());
    }

    @Test
    void toString_shouldContainRideInfo() {
        // When
        String rideString = ride.toString();
        
        // Then
        assertTrue(rideString.contains(rideId));
        assertTrue(rideString.contains(rider.getName()));
        assertTrue(rideString.contains(driver.getName()));
        assertTrue(rideString.contains(RideStatus.REQUESTED.toString()));
    }
}
