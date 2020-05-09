package com.lvdreamer.structure.bridge;

import com.lvdreamer.build.abstractfactory.Car;

public abstract class CarBridge {
    protected Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public abstract void run();
}
