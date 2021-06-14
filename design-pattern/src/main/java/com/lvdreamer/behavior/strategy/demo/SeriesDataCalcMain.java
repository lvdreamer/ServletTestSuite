package com.lvdreamer.behavior.strategy.demo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SeriesDataCalcMain {
    public static void main(String[] args) {
        Set<Integer> targetCalData = new HashSet<>();
        List<SeriesDefine> seriesDefines = new ArrayList<>();
        SimpleCalcContext defaultCalcContext = new SimpleCalcContext(targetCalData, seriesDefines);
        List<SeriesData> boolSeriesDataList = DataCalculatoerLoader.getDataCalculator(SeriesDataTypeEnum.BOOL.getValue()).calcSeriesData(defaultCalcContext);
        System.out.println("bool calc result:" + boolSeriesDataList);
        List<SeriesData> topNSeriesDataList = DataCalculatoerLoader.getDataCalculator(SeriesDataTypeEnum.TOPN.getValue()).calcSeriesData(defaultCalcContext);
        System.out.println("topN calc result:" + topNSeriesDataList);
        List<SeriesData> simpleSeriesDataList = DataCalculatoerLoader.getDataCalculator(SeriesDataTypeEnum.DEFAULT.getValue()).calcSeriesData(defaultCalcContext);
        System.out.println("simple calc result:" + simpleSeriesDataList);
    }
}
