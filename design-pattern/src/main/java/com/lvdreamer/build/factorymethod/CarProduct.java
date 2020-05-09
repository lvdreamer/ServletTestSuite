package com.lvdreamer.build.factorymethod;

public class CarProduct implements Product {
    @Override
    public String getName() {
        return "Car";
    }

    @Override
    public String toString() {
        return getName();
    }
}
