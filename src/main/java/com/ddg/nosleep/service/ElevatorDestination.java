package com.ddg.nosleep.service;

import java.util.TreeSet;

public class ElevatorDestination {
    //Главная очередь
    private TreeSet<Integer> waiters = new TreeSet<>();

    public ElevatorDestination() {
    }

    public TreeSet<Integer> getWaiters() {
        return waiters;
    }

    public void setWaiters(TreeSet<Integer> waiters) {
        this.waiters = waiters;
    }

}
