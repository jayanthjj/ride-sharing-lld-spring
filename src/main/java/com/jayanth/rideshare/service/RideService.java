package com.jayanth.rideshare.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.jayanth.rideshare.model.*;
import com.jayanth.rideshare.strategy.FareStrategy;
import com.jayanth.rideshare.observer.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class RideService {
    private final Map<String, Ride> rideHistory = new HashMap<>();
    private final DriverService driverService;
    private final NotificationService notificationService;

    public RideService(DriverService driverService, NotificationService notificationService) {
        this.driverService = driverService;
        this.notificationService = notificationService;
    }

    public Ride createRide(Rider rider, Location dropLocation) {
        Driver driver = driverService.findNearestAvailableDriver(rider.getCurrentLocation());
        if (driver == null) {
            System.out.println("No available drivers for rider " + rider.getName());
            return null;
        }

        Ride ride = new Ride(UUID.randomUUID().toString(), rider, driver, rider.getCurrentLocation(), dropLocation);
        driver.setAvailable(false);

        rideHistory.put(ride.getId(), ride);
        notificationService.notifyAllObservers(driver, "You have been assigned a ride from " + rider.getName());

        return ride;
    }

    public void completeRide(String rideId, FareStrategy strategy) {
        Ride ride = rideHistory.get(rideId);
        if (ride == null) {
            System.out.println("Ride not found.");
            return;
        }
        ride.startRide();
        ride.endRide(strategy);
        ride.getDriver().setAvailable(true);
    }

    public Map<String, Ride> getRideHistory() {
        return rideHistory;
    }
}
