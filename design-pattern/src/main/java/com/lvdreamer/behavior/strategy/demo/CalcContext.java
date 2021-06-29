package com.lvdreamer.behavior.strategy.demo;

import java.util.List;
import java.util.Set;

public interface CalcContext {

    Set<Integer> getCalData();

    List<SeriesDefine> getSeriesDefines();
}
