package com.jayanth.rideshare.strategy;

import com.jayanth.rideshare.model.Location;

public interface FareStrategy {
    double calculateFare(Location start, Location end);
}