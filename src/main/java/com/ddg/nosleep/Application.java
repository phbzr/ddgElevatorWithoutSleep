package com.ddg.nosleep;

import com.ddg.nosleep.service.ElevatorStatus;
import com.ddg.nosleep.service.TripTime;
import com.ddg.nosleep.service.ElevatorControlPanel;
import com.ddg.nosleep.service.ElevatorDestination;
import com.ddg.nosleep.service.ElevatorMoving;
import com.ddg.nosleep.domain.ElevatorState;
import com.ddg.nosleep.model.Elevator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
         SpringApplication.run(Application.class, args);
    }

    @Bean
    public TripTime tripTime(){
        return new TripTime();
    }

    @Bean
    public ElevatorState elevatorState(){
        return new ElevatorState();
    }

    @Bean
    public ElevatorControlPanel elevatorControlPanel() {
        return new ElevatorControlPanel();
    }

    @Bean
    public ElevatorMoving elevatorMoving(){
        return new ElevatorMoving();
    }

    @Bean
    public ElevatorDestination elevatorDestination(){
        return new ElevatorDestination();
    }

    @Bean
    public ElevatorStatus elevatorStatus(){
        return new ElevatorStatus();
    }

    @Bean
    public Elevator elevator(){
        return new Elevator();
    }

}
