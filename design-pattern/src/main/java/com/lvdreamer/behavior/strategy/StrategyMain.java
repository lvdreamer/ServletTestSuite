package com.lvdreamer.behavior.strategy;

import com.google.common.base.Splitter;

import java.util.regex.Pattern;

public class StrategyMain {
    public static void main(String[] args) {
        String strr = "a,,b,c,d,e";
        Iterable<String> iterable = Splitter.on(',').split(strr);
        for (String result : iterable) {
            System.out.println(result);

        }
        System.out.println("====================================");
        iterable = Splitter.on(Pattern.compile("c")).split(strr);
        for (String result : iterable) {
            System.out.println(result);

        }
    }
}