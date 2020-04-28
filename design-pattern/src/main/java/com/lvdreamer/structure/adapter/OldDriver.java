package com.lvdreamer.structure.adapter;

public class OldDriver {
    private StdCar stdCar;

    public OldDriver(StdCar stdCar) {
        this.stdCar = stdCar;
    }

    public void drive() {
        stdCar.run();
    }
}
