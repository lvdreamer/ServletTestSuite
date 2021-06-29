package com.lvdreamer.behavior.strategy.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoolSeriesDataCalculator extends AbstractSeriesDataCalculator {
    @Override
    public CalcContext preHandler(CalcContext calcContext) {
        return calcContext;
    }

    @Override
    public List<SeriesData> postHandler(CalcContext calcContext, List<SeriesData> seriesDataList) {
        if (null == seriesDataList || seriesDataList.size() == 0) {
            return seriesDataList;
        }
        List<SeriesData> boolSeriesDatas = new ArrayList<>(2);
        SeriesData trueData = new SeriesData();
        trueData.setName("是");
        trueData.setValue(BigDecimal.ZERO);
        boolSeriesDatas.add(trueData);
        SeriesData falseData = new SeriesData();
        falseData.setName("否");
        falseData.setValue(BigDecimal.ZERO);
        boolSeriesDatas.add(falseData);
        /**
         * 汇总否的数据
         */
        Iterator<SeriesData> seriesDataIterator = seriesDataList.iterator();
        while (seriesDataIterator.hasNext()) {
            SeriesData seriesData = seriesDataIterator.next();
            if ("否".equals(seriesData.getName()) || "其他".equals(seriesData.getName())) {
                falseData.setValue(falseData.getValue().add(seriesData.getValue()));
                seriesDataIterator.remove();
            }
        }
        /**
         * 汇总是的数据
         */
        Iterator<SeriesData> trueDataIterator = seriesDataList.iterator();
        while (trueDataIterator.hasNext()) {
            SeriesData seriesData = trueDataIterator.next();
            trueData.setValue(trueData.getValue().add(seriesData.getValue()));
        }
        this.fillPercent(boolSeriesDatas, calcContext.getCalData().size());
        return boolSeriesDatas;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DataCalculatoerLoader.register(SeriesDataTypeEnum.BOOL.getValue(), this);
    }
}
