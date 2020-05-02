package com.lvdreamer.structure.facade;

public class SecendWorker extends AbstractWorker {

    @Override
    public void work() {
        System.out.println(workerName() + " carry the box");
    }

    @Override
    public String workerName() {
        return "secend worker";
    }
}
