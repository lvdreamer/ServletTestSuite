package com.lvdreamer.structure.bridge;

import com.lvdreamer.build.abstractfactory.BmwCar;

public class BridgeMain {
    public static void main(String[] args) {
        CarBridge carBridge = new BenzCarBridge();
        carBridge.setCar(new BmwCar());
        carBridge.run();
        CarBridge carBridge2 = new BwmCarBridge();
        carBridge2.setCar(new BmwCar());
        carBridge2.run();
    }
}
