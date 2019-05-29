package com.ddg.nosleep.domain;

public class ElevatorStatus {
    // 300 - Нет оповещения
    // 500 - Оповещение
    private String checker = "300";

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }
}
