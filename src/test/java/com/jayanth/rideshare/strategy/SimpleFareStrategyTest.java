package com.jayanth.rideshare.strategy;

import com.jayanth.rideshare.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleFareStrategyTest {

    private SimpleFareStrategy fareStrategy;
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;

    @BeforeEach
    void setUp() {
        fareStrategy = new SimpleFareStrategy();
    }

    @Test
    void calculateFare_whenZeroDistance_shouldReturnBaseFare() {
        // Given
        Location start = new Location(10.0, 20.0);
        Location end = new Location(10.0, 20.0); // Same location
        
        // When
        double fare = fareStrategy.calculateFare(start, end);
        
        // Then
        assertEquals(BASE_FARE, fare, 0.001);
    }

    @Test
    void calculateFare_whenNonZeroDistance_shouldReturnBaseFarePlusDistanceRate() {
        // Given
        Location start = new Location(0.0, 0.0);
        Location end = new Location(3.0, 4.0); // 5 units distance using Euclidean distance
        
        // When
        double fare = fareStrategy.calculateFare(start, end);
        
        // Then - BASE_FARE + (5 * PER_KM_RATE)
        double expectedFare = BASE_FARE + (5.0 * PER_KM_RATE);
        assertEquals(expectedFare, fare, 0.001);
    }

    @Test
    void calculateFare_withRealWorldLocations_shouldCalculateCorrectly() {
        // Given - Real world coordinates (approximately)
        Location bangalore = new Location(12.9716, 77.5946);
        Location chennai = new Location(13.0827, 80.2707);
        
        // When
        double fare = fareStrategy.calculateFare(bangalore, chennai);
        
        // Then - Expect fare calculation based on distance between these cities
        double distance = bangalore.distanceTo(chennai);
        double expectedFare = BASE_FARE + (distance * PER_KM_RATE);
        assertEquals(expectedFare, fare, 0.001);
    }

    @Test
    void calculateFare_withNegativeCoordinates_shouldCalculateCorrectly() {
        // Given
        Location location1 = new Location(-10.0, -20.0);
        Location location2 = new Location(-13.0, -24.0);
        
        // When
        double fare = fareStrategy.calculateFare(location1, location2);
        
        // Then
        double distance = location1.distanceTo(location2);
        double expectedFare = BASE_FARE + (distance * PER_KM_RATE);
        assertEquals(expectedFare, fare, 0.001);
    }
}