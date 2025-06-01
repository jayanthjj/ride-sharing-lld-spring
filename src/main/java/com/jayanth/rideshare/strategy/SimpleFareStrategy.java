package com.jayanth.rideshare.strategy;

import com.jayanth.rideshare.model.Location;

public class SimpleFareStrategy implements FareStrategy {
    private static final double BASE_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;

    @Override
    public double calculateFare(Location start, Location end) {
        double distance = start.distanceTo(end);
        return BASE_FARE + (distance * PER_KM_RATE);
    }
}
