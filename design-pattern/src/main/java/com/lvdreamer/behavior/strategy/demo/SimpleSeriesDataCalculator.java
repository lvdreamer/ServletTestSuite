package com.lvdreamer.behavior.strategy.demo;

import java.util.List;

public class SimpleSeriesDataCalculator extends AbstractSeriesDataCalculator {

    @Override
    public void afterPropertiesSet() throws Exception {
        DataCalculatoerLoader.register(SeriesDataTypeEnum.DEFAULT.getValue(), this);
    }

    @Override
    public CalcContext preHandler(CalcContext calcContext) {
        return calcContext;
    }

    @Override
    public List<SeriesData> postHandler(CalcContext calcContext,List<SeriesData> seriesDataList) {
        return seriesDataList;
    }
}
