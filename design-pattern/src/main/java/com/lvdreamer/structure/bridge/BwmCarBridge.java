package com.lvdreamer.structure.bridge;

public class BwmCarBridge extends CarBridge {

    @Override
    public void run() {
        System.out.println(car.getSpeed());
    }
}
