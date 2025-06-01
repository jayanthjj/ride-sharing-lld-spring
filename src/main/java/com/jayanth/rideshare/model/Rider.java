package com.jayanth.rideshare.model;

import java.util.UUID;

public class Rider {
    private final String id;
    private final String name;
    private Location currentLocation;

    public Rider(String name, Location location){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.currentLocation = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location=(" + currentLocation.getLatitude() + ", " + currentLocation.getLongitude() + ")" +
                '}';
    }
}
