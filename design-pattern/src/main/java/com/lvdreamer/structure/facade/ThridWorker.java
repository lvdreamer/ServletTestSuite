package com.lvdreamer.structure.facade;

public class ThridWorker extends AbstractWorker {

    @Override
    public void work() {
        System.out.println(workerName() + " send the box");
    }

    @Override
    public String workerName() {
        return "thrid worker";
    }
}
