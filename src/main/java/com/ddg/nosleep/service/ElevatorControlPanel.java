package com.ddg.nosleep.service;

import com.ddg.nosleep.model.Elevator;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.TreeSet;

public class ElevatorControlPanel {

    @Autowired
    private Elevator elevator;
    @Autowired
    private ElevatorMoving elevatorMoving;
    @Autowired
    private TripTime tripTime;

    private int min = 500;
    private int tmp = 0;
    private int dest = -1; //конечная точка
    private TreeSet<Integer> localUp = new TreeSet<>(); //очередь вверх
    private TreeSet<Integer> localDown = new TreeSet<>();// очередь вниз

    public TreeSet<Integer> getLocalUp() {
        return localUp;
    }

    public void setLocalUp(TreeSet<Integer> localUp) {
        this.localUp = localUp;
    }

    public void setLocalDown(TreeSet<Integer> localDown) {
        this.localDown = localDown;
    }

    public TreeSet<Integer> getLocalDown() {
        return localDown;
    }

    public ElevatorControlPanel() {
    }

    /*
     добавляем пункт назначения с очереди пунктов
    */

    public void addDestination(TreeSet<Integer> queue) {
        /*
        Если очередь не пустая добавляем пункт назначаения с первого элемента в очереди
        Перебираем элементы в очереди, устанавливаем пункт назначени ближайший к текущему этажу
        */
        if (queue.size() > 0) {
        for (int oneOf : queue){
            int mex = Math.abs(elevator.getCurrentFloor() - oneOf);
            if (mex < min){
                min = mex;
                tmp = oneOf;
            }
        }
            dest = tmp;
            queue.remove(tmp);
        }
         /*
        проверяем пункт назначения(1-7 этаж)
        рапределяем по очередям вверх\вниз
        назначаем предыдущий этаж
        запускаем в работу
        */
        if (dest > 0 && dest < 8) {
            if (dest < elevator.getCurrentFloor()) {
                localDown.add(dest);
            }
            if (dest > elevator.getCurrentFloor()) {
                localUp.add(dest);
            }
            elevatorMoving.work();
        }
         /*
        Если в очереди есть элементы продолжаем работу,
         если нет очищаем времянной интервал
        */
        min = 500;
        if (queue.size()!=0){
            addDestination(queue);
        }else {
            tripTime.getTripTime().set(0);
        }

    }
}
