package com.lvdreamer.build.abstractfactory;

public class BmwCar implements Car {
    private static final int SPEED = 100;

    @Override
    public int getSpeed() {
        return SPEED;
    }
}
