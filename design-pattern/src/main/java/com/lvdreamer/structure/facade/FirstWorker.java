package com.lvdreamer.structure.facade;

public class FirstWorker extends AbstractWorker {

    @Override
    public void work() {
        System.out.println(workerName() + " make the box");
    }

    @Override
    public String workerName() {
        return "frist worker";
    }
}
