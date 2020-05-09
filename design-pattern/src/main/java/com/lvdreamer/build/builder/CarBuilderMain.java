package com.lvdreamer.build.builder;

public class CarBuilderMain {
    public static void main(String[] args) {
        CarBuilder carBuilder = new CarBuilder.Builder("four", "one").builder();
        System.out.println(carBuilder);
    }
}
