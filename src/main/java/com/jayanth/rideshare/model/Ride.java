package com.jayanth.rideshare.model;

import com.jayanth.rideshare.strategy.FareStrategy;

public class Ride {
    private final String id;
    private final Rider rider;
    private final Driver driver;
    private final Location pickupLocation;
    private final Location dropLocation;
    private RideStatus status;
    private double fare;

    public Ride(String id, Rider rider, Driver driver, Location pickupLocation, Location dropLocation) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.status = RideStatus.REQUESTED;
    }

    public String getId() {
        return id;
    }

    public void startRide() {
        if (status == RideStatus.REQUESTED) {
            status = RideStatus.IN_PROGRESS;
            System.out.println("Ride started for " + rider.getName());
        }
    }

    public void endRide(FareStrategy fareStrategy) {
        if (status == RideStatus.IN_PROGRESS) {
            status = RideStatus.COMPLETED;
            fare = fareStrategy.calculateFare(pickupLocation, dropLocation);
            System.out.println("Ride completed. Fare: ₹" + fare);
        }
    }

    public Rider getRider() { return rider; }
    public Driver getDriver() { return driver; }
    public RideStatus getStatus() { return status; }
    public double getFare() { return fare; }

    @Override
    public String toString() {
        return "Ride{" +
                "id='" + id + '\'' +
                ", rider=" + rider.getName() +
                ", driver=" + driver.getName() +
                ", fare=₹" + fare +
                ", status=" + status +
                '}';
    }
}
