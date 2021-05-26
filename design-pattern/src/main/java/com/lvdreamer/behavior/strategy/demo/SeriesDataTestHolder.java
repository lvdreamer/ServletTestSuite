package com.lvdreamer.behavior.strategy.demo;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SeriesDataTestHolder {

    private static final ConcurrentHashMap<String, Set<Integer>> dataMap = new ConcurrentHashMap<>();

    private SeriesDataTestHolder() {

    }

    public static Set<Integer> get(String key) {
        return dataMap.get(key);
    }
}
