package com.lvdreamer.behavior.strategy.demo;

import java.util.List;
import java.util.Set;

public class SimpleCalcContext implements CalcContext {
    private Set<Integer> targetCalData;
    private List<SeriesDefine> seriesDefines;

    public SimpleCalcContext(Set<Integer> targetCalData, List<SeriesDefine> seriesDefines) {
        this.targetCalData = targetCalData;
        this.seriesDefines = seriesDefines;
    }


    @Override
    public Set<Integer> getCalData() {
        return targetCalData;
    }

    @Override
    public List<SeriesDefine> getSeriesDefines() {
        return seriesDefines;
    }
}
