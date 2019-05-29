package com.ddg.nosleep.service;

import java.util.concurrent.atomic.AtomicInteger;

public class TripTime {
    private AtomicInteger tripTime = new AtomicInteger(0);

    public AtomicInteger getTripTime() {
        return tripTime;
    }

    public void setTripTime(AtomicInteger tripTime) {
        this.tripTime = tripTime;
    }
}
