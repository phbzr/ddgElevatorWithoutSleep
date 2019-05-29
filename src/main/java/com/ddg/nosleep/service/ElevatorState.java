package com.ddg.nosleep.service;

import com.ddg.nosleep.model.Elevator;
import org.springframework.beans.factory.annotation.Autowired;

public class ElevatorState {
    @Autowired
    private Elevator elevator;
    private String textResponse;
    private String message;

    public String getTextResponse() {
        return textResponse;
    }
    public void setTextResponse(String textResponse) {
        this.textResponse = textResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = Integer.toString(elevator.getCurrentFloor());
    }

}
