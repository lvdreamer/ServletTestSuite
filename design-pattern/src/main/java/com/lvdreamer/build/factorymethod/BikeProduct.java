package com.lvdreamer.build.factorymethod;

public class BikeProduct implements Product {
    @Override
    public String getName() {
        return "Bike";
    }
    @Override
    public String toString() {
        return getName();
    }
}
