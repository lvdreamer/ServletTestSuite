package com.lvdreamer.structure.proxy;

public class SourceProxy implements SourceInterface {
    private SourceObj sourceObj;

    public SourceProxy(SourceObj sourceObj) {
        this.sourceObj = sourceObj;
    }

    @Override
    public void work() {
        if (sourceObj.isBusy()) {
            System.out.println("source is busy,proxy work instead");
        } else {
            sourceObj.work();
        }
    }
}
