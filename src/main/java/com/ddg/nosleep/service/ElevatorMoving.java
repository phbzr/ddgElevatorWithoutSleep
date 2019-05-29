package com.ddg.nosleep.service;

import com.ddg.nosleep.domain.ElevatorStatus;
import com.ddg.nosleep.model.Elevator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.TreeSet;

public class ElevatorMoving {
    @Autowired
    private ElevatorDestination elevatorDestination;

    @Autowired
    private ElevatorControlPanel elevatorControlPanel;

    @Autowired
    private ElevatorStatus elevatorStatus;

    @Autowired
    private Elevator elevator;

    @Autowired
    private TripTime tripTime;

    public ElevatorMoving() {
    }
    /*
        Взависимости от очередей запускаем подходящий метод
     */
    public void work() {

        if ((elevator.getCurrentFloor() == 1 || elevatorControlPanel.getLocalDown().size() == 0) && elevatorControlPanel.getLocalUp().size() > 0) {
            //Не даем уйти в другой метод , пока не опустошим список
            while (elevatorControlPanel.getLocalUp().size() > 0) {
                elevatorUp(elevatorControlPanel.getLocalUp());
            }

        } else if (elevator.getCurrentFloor() > 1 && elevatorControlPanel.getLocalDown().size() > 0) {
            //Не даем уйти в другой метод , пока не опустошим список
            while (elevatorControlPanel.getLocalDown().size() > 0) {
                elevatorDown(elevatorControlPanel.getLocalDown());
            }
        }

    }

    private void elevatorDown(TreeSet<Integer> local) {
        try {
            //Проверяем, чтобы лишний раз не уехать за допустимые значения
            //Процес движения
            while (elevator.getCurrentFloor() != local.last() && elevator.getCurrentFloor() < 8 && elevator.getCurrentFloor() > 0) {
                //Если текущий этаж содержится в основной очереди, вызываем остановку
                if (elevatorDestination.getWaiters().contains(elevator.getCurrentFloor())) {
                    exitSender();
                    elevatorDestination.getWaiters().remove(elevator.getCurrentFloor());
                }

                System.out.println("Текущий этаж: " + elevator.getCurrentFloor());
                tripTime.getTripTime().getAndAdd(10);
               // TimeUnit.SECONDS.sleep(10);//Движение между этажами
                //берем текущий этаж уменьшаем и сетаем в текущий этаж
                int x = elevator.getCurrentFloor();
                x--;
                elevator.setCurrentFloor(x);
            }
            /*
            Текущий этаж равен пункту назначения из локальной очереди
            сетаем его вызываем оповещение
            удаляем из очереди
             */
            elevator.setCurrentFloor(elevator.getCurrentFloor());
            exitSender();
            local.pollLast();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void elevatorUp(TreeSet<Integer> local) {
        try {
            //Проверяем, чтобы лишний раз не уехать за допустимые значения
            //Процес движения
            while (elevator.getCurrentFloor() != local.first() && elevator.getCurrentFloor() < 8 && elevator.getCurrentFloor() > 0) {
                //Если текущий этаж содержится в основной очереди, вызываем остановку
                if (elevatorDestination.getWaiters().contains(elevator.getCurrentFloor())) {
                    exitSender();
                    elevatorDestination.getWaiters().remove(elevator.getCurrentFloor());
                }

                System.out.println("Текущий этаж: " + elevator.getCurrentFloor());
                tripTime.getTripTime().getAndAdd(10);
               // TimeUnit.SECONDS.sleep(10);//Движение между этажами
                //берем текущий этаж увеличиваем и сетаем в текущий этаж
                int x = elevator.getCurrentFloor();
                x++;
                elevator.setCurrentFloor(x);

            }
            /*
            Текущий этаж равен пункту назначения из локальной очереди
            Сетаем его вызываем оповещение
            Удаляем из очереди
             */
            elevator.setCurrentFloor(elevator.getCurrentFloor());
            exitSender();
            local.pollFirst();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //вызываем оповещения
    public void exitSender() throws InterruptedException {
        elevatorStatus.setChecker("500");
        tripTime.getTripTime().getAndAdd(2);
        System.out.println("Прибыли на " + elevator.getCurrentFloor() + " этаж. Время в пути " + tripTime.getTripTime() + " секунды");
     //   TimeUnit.SECONDS.sleep(4);
        elevatorStatus.setChecker("300");
        tripTime.getTripTime().getAndAdd(2);
    }

}
