package com.ddg.nosleep.service;

import com.ddg.nosleep.model.Elevator;
import org.springframework.beans.factory.annotation.Autowired;

public class ElevatorState {
    @Autowired
    private Elevator elevator;

    private String message;
    private String exitStatus;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = Integer.toString(elevator.getCurrentFloor());
    }

    public String getExitStatus() {
        return exitStatus;
    }

    public void setExitStatus(String exitStatus) {
        this.exitStatus = exitStatus;
    }
}
