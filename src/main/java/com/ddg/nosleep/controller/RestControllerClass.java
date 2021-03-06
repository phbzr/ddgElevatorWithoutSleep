package com.ddg.nosleep.controller;

import com.ddg.nosleep.service.ElevatorDestination;
import com.ddg.nosleep.service.ElevatorControlPanel;
import com.ddg.nosleep.service.ElevatorStatus;
import com.ddg.nosleep.domain.ElevatorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ddg.nosleep.domain.ResponseObject;

@RestController
public class RestControllerClass {
    @Autowired
    private ElevatorDestination elevatorDestination;

    @Autowired
    private ElevatorStatus elevatorStatus;

    @Autowired
    private ElevatorState elevatorState;

    @Autowired
    private ElevatorControlPanel elevatorControlPanel;
    /*
        Получаем пункт назначения
    */
    @PostMapping("/api/destination")
    public void getDestination(@RequestBody ResponseObject object) {
        elevatorDestination.getWaiters().add(object.getId());
    }
    /*
        Отдаем текущий этаж
        и статус остановки
    */
    @PostMapping("/api/state")
    public ResponseEntity<ElevatorState> currentFloorStatus() {
        elevatorState.setMessage("");
        elevatorState.setTextResponse(elevatorStatus.getElevatorStatus().toString());
        elevatorStatus.setElevatorStatus(new StringBuilder());
        return new ResponseEntity<>(elevatorState, HttpStatus.OK);
    }

    @PostMapping("/api/launcher")
    public void getStarted(){
        elevatorControlPanel.addDestination(elevatorDestination.getWaiters());
    }
}
