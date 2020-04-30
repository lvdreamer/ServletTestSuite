package com.lvdreamer.structure.bridge;

public class BenzCarBridge extends CarBridge {

    @Override
    public void run() {
        System.out.println(car.getSpeed());
    }
}
