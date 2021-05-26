package com.lvdreamer.behavior.strategy.demo;

import java.util.List;

/**
 * @author wanghb11
 * @version 1.0
 * @date 2021/5/21 11:11
 * @description 计算系列数据
 **/
public interface SeriesDataCalculator {
    CalcContext preHandler(CalcContext calcContext);

    List<SeriesData> calcSeriesData(CalcContext calcContext);

    List<SeriesData> postHandler(CalcContext calcContext, List<SeriesData> seriesDataList);
}
