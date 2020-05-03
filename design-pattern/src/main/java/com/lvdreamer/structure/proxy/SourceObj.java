package com.lvdreamer.structure.proxy;

public class SourceObj implements SourceInterface {

    private boolean isBusy;

    public void work() {
        System.out.println("working ");
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
