package com.lvdreamer.structure.adapter;

public class BenzCarAdapter implements StdCar {

    private BenzCar benzCar;

    public BenzCarAdapter(BenzCar benzCar) {
        this.benzCar = benzCar;
    }

    @Override
    public void run() {
        benzCar.runFast();
    }
}
