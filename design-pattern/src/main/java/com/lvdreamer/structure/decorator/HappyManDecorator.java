package com.lvdreamer.structure.decorator;

public class HappyManDecorator implements Man {

    private Man man;

    public HappyManDecorator(Man man) {
        this.man = man;
    }

    @Override
    public void laugh() {
        man.laugh();
    }

    @Override
    public void cry() {
        man.cry();
        System.out.println("喜极而泣");

    }
}
