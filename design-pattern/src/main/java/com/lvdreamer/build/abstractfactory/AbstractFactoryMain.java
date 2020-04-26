package com.lvdreamer.build.abstractfactory;

public class AbstractFactoryMain {
    public static void main(String[] args) {
        CarFactory bmwFactory = new BmwFactory();
        Car bmwCar = bmwFactory.produce();
        System.out.println("The bmw car speed:" + bmwCar.getSpeed());
        CarFactory benzFactory = new BenzFactory();
        Car benzCar = benzFactory.produce();
        System.out.println("The benz car speed:" + benzCar.getSpeed());
    }
}
