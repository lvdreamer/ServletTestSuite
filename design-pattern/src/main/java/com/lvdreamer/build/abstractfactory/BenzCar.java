package com.lvdreamer.build.abstractfactory;

public class BenzCar implements Car {
    private static final int SPEED = 110;
    public int getSpeed() {
        return SPEED;
    }
}
