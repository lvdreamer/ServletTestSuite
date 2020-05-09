package com.lvdreamer.structure.decorator;

public class DecoratorMain {
    public static void main(String[] args) {
        Man simpleMan = new SimpleMan();
        simpleMan.laugh();
        simpleMan.cry();
        System.out.println("割割割割割割割割割割割割割割");
        Man happyMan = new HappyManDecorator(simpleMan);
        happyMan.laugh();
        happyMan.cry();
        System.out.println("割割割割割割割割割割割割割割");
        Man sadMan = new SadManDecorator(simpleMan);
        sadMan.laugh();
        sadMan.cry();
    }
}
