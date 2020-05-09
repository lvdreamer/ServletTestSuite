package com.lvdreamer.basic.proxy;

public class OriginalObject implements OriginalInterface{
    @Override
    public void sayHello() {
        System.out.println("hello!");
    }
}
