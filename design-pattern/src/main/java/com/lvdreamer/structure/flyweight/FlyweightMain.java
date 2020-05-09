package com.lvdreamer.structure.flyweight;

public class FlyweightMain {

    public static void main(String[] args) {
        /**
         * Integer 针对-128~127有缓存
         */
        Integer a = Integer.valueOf(10);
        Integer b = Integer.valueOf(10);
        System.out.println(a == b);
        Integer c = Integer.valueOf(133);
        Integer d = Integer.valueOf(133);
        System.out.println(c == d);

    }
}
