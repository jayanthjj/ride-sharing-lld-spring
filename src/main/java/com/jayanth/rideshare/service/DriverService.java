package com.jayanth.rideshare.service;

import java.util.ArrayList;
import java.util.List;
import com.jayanth.rideshare.model.Driver;
import com.jayanth.rideshare.model.Location;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    private final List<Driver> drivers = new ArrayList<>();

    public void registerDriver(Driver driver) {
        drivers.add(driver);
    }

    public Driver findNearestAvailableDriver(Location location) {
        Driver nearest = null;
        double minDist = Double.MAX_VALUE;
        for (Driver d : drivers) {
            if (!d.isAvailable()) continue;
            double dist = d.getCurrentLocation().distanceTo(location);
            if (dist < minDist) {
                minDist = dist;
                nearest = d;
            }
        }
        return nearest;
    }
}
