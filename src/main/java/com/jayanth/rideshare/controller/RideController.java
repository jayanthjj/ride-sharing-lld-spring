package com.jayanth.rideshare.controller;

import com.jayanth.rideshare.model.*;
import com.jayanth.rideshare.service.*;
import com.jayanth.rideshare.strategy.SimpleFareStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @Autowired
    private DriverService driverService;

    @PostMapping("/registerDriver")
    public String registerDriver(@RequestBody Driver driver) {
        driverService.registerDriver(driver);
        return "Driver registered: " + driver.getName();
    }

    @PostMapping("/book")
    public String bookRide(@RequestBody Rider rider, @RequestParam double dropLat, @RequestParam double dropLng) {
        Location drop = new Location(dropLat, dropLng);
        Ride ride = rideService.createRide(rider, drop);
        if (ride == null) {
            return "No drivers available";
        }
        return "Ride booked with driver: " + ride.getDriver().getName() + ", Ride ID: " + ride.getId();
    }

    @PostMapping("/complete")
    public String completeRide(@RequestParam String rideId) {
        rideService.completeRide(rideId, new SimpleFareStrategy());
        return "Ride " + rideId + " completed.";
    }

    @GetMapping("/history")
    public Collection<Ride> getAllRides() {
        return rideService.getRideHistory().values();
    }
}
