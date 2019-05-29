package com.ddg.nosleep.model;

public class Elevator {
    private volatile int currentFloor = 1;

    public Elevator() {
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

}
