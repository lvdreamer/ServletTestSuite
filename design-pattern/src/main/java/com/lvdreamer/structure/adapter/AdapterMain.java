package com.lvdreamer.structure.adapter;

public class AdapterMain {
    public static void main(String[] args) {
        OldDriver driver = new OldDriver(new BmwCar());
        driver.drive();
        driver = new OldDriver(new BenzCarAdapter(new BenzCar()));
        driver.drive();
    }
}
