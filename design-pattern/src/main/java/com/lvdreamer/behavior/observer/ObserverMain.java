package com.lvdreamer.behavior.observer;

import java.util.Observable;

public class ObserverMain {
    public static void main(String[] args) {
        Observable observable = new Observable() {
            @Override
            public void notifyObservers(Object arg) {
                super.setChanged();
                super.notifyObservers(arg);
            }
        };
        observable.addObserver((Observable o, Object arg) -> {
            System.out.println("receive msg");
        });
        observable.addObserver((Observable o, Object arg) -> {
            System.out.println("receive msg 2");
        });
        observable.addObserver((Observable o, Object arg) -> {
            System.out.println("receive msg 3");
        });
        observable.notifyObservers();
    }
}
