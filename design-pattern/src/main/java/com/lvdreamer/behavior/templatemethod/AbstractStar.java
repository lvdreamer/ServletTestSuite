package com.lvdreamer.behavior.templatemethod;

public abstract class AbstractStar {

    protected void talent() {
        sing();
        jump();
        rap();
    }


    protected abstract void rap();

    protected abstract void jump();

    protected abstract void sing();
}
