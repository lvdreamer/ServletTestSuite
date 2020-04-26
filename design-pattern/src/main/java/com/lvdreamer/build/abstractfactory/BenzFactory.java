package com.lvdreamer.build.abstractfactory;

public class BenzFactory implements CarFactory {
    @Override
    public Car produce() {
        return new BenzCar();
    }
}
