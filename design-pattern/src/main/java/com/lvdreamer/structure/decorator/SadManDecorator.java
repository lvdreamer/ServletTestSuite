package com.lvdreamer.structure.decorator;

public class SadManDecorator implements Man {

    private Man man;

    public SadManDecorator(Man man) {
        this.man = man;
    }

    @Override
    public void laugh() {
        man.laugh();
        System.out.println("我的笑只是我穿的保护色");
    }

    @Override
    public void cry() {
        man.cry();
    }
}
