package com.lvdreamer.structure.decorator;

public class SimpleMan implements Man {

    @Override
    public void laugh() {
        System.out.println("just laugh");
    }

    @Override
    public void cry() {
        System.out.println("just cry");
    }
}
