package com.lvdreamer.basic.regex;

public class InheritMethodTest {

    public static void main(String[] args) {
        Cat c1 = new Cat();
        c1.show();
    }
}

class Animal {
    public void function() {
        show();
    }

    // 父类的私有方法，不被子类看见
    private void show() {
        System.out.println("父类的方法");
    }
}

class Cat extends Animal {


    // 此处为子类自定义，而不叫重写，因为子类无法看到且无法访问父类的私有show()方法
    public void show() {
        System.out.println("子类的私有方法");
        System.out.println("--------------");
    }
}