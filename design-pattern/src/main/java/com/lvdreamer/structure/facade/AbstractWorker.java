package com.lvdreamer.structure.facade;

public abstract class AbstractWorker {
    public abstract void work();


    public void toTheNext() {
        System.out.println(workerName() + ":I am done,now work to next");
    }

    public abstract String workerName();
}
