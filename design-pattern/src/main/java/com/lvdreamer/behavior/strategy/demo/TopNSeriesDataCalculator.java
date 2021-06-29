package com.lvdreamer.behavior.strategy.demo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TopNSeriesDataCalculator extends AbstractSeriesDataCalculator {
    private static final int DEFAULT_TOP_NUM = 10;

    @Override
    public CalcContext preHandler(CalcContext calcContext) {
        return calcContext;
    }

    @Override
    public List<SeriesData> postHandler(CalcContext calcContext, List<SeriesData> seriesDataList) {
        //去除 其他 分类
        this.removeLastOtherData(seriesDataList);
        //只展示topN
        return seriesDataList.stream().sorted(Comparator.comparing(SeriesData::getValue).reversed())
                .limit(DEFAULT_TOP_NUM)
                .collect(Collectors.toList());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DataCalculatoerLoader.register(SeriesDataTypeEnum.TOPN.getValue(), this);
    }
}
