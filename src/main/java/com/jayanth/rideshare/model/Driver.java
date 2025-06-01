package com.jayanth.rideshare.model;

import java.util.UUID;

public class Driver {
    private final String id;
    private final String name;
    private Location currentLocation;
    private boolean isAvailable;

    public Driver(String name, Location location) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.currentLocation = location;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Location getCurrentLocation() { return currentLocation; }

    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public void updateLocation(Location location) {
        this.currentLocation = location;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", available=" + isAvailable +
                ", location=(" + currentLocation.getLatitude() + ", " + currentLocation.getLongitude() + ")" +
                '}';
    }
}
