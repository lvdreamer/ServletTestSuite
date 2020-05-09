package com.lvdreamer.structure.facade;

public class FacadeMain {
    public static void main(String[] args) {
        CenterWorkerFacade facade = new CenterWorkerFacade();
        facade.offerABox();
    }
}
