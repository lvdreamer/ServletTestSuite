package com.lvdreamer.build.factorymethod;

public class BmwFactory {
    Product produce(ProductType productType) {
        switch (productType) {
            case CAR:
                return new CarProduct();
            case BIKE:
                return new BikeProduct();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        BmwFactory factory = new BmwFactory();
        System.out.println(factory.produce(ProductType.BIKE));
        System.out.println(factory.produce(ProductType.CAR));

    }
}
