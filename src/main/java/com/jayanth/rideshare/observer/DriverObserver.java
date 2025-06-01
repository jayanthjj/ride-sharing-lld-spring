package com.jayanth.rideshare.observer;

import com.jayanth.rideshare.model.Driver;

public interface DriverObserver {
    void notifyDriverAssigned(Driver driver, String message);
}
