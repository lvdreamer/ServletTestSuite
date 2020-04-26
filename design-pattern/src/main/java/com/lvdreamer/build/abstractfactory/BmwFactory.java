package com.lvdreamer.build.abstractfactory;

public class BmwFactory implements CarFactory {
    @Override
    public Car produce() {
        return new BmwCar();
    }
}
