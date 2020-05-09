package com.lvdreamer.structure.facade;

import java.util.ArrayList;
import java.util.List;

public class CenterWorkerFacade {

    private List<AbstractWorker> workers;

    public CenterWorkerFacade() {
        workers = new ArrayList<>();
        workers.add(new FirstWorker());
        workers.add(new SecendWorker());
        workers.add(new ThridWorker());

    }
    public void offerABox() {
        workers.forEach(AbstractWorker::work);
    }
}
