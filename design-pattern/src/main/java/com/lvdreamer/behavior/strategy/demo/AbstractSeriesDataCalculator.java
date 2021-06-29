package com.lvdreamer.behavior.strategy.demo;

import com.google.common.collect.Sets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public abstract class AbstractSeriesDataCalculator implements SeriesDataCalculator, InitializingBean {
    @Override
    public List<SeriesData> calcSeriesData(CalcContext calcContext) {
        calcContext = this.preHandler(calcContext);
        Set<Integer> targetSet = calcContext.getCalData();
        int targetTotalCount = targetSet.size();
        List<SeriesDefine> currSeriesDefines = calcContext.getSeriesDefines();
        Integer targetCount = 0;
        List<SeriesData> seriesDataList = new ArrayList<>(currSeriesDefines.size() + 1);
        for (SeriesDefine valueMap : currSeriesDefines) {
            SeriesData seriesData = new SeriesData();
            seriesData.setName(valueMap.getRemark());
            String key = valueMap.getLabelNum() + "-" + valueMap.getLabelMap();
            Set<Integer> current = SeriesDataTestHolder.get(key);
            if (current == null) {
                seriesData.setValue(new BigDecimal(0));
            } else {
                Set<Integer> targetCurrent = Sets.union(current,targetSet);
                int currCount = targetCurrent.size();
                targetCount += currCount;
                seriesData.setValue(new BigDecimal(currCount));
            }
            seriesDataList.add(seriesData);
        }
        //有未知用户
        if (targetTotalCount - targetCount > 0) {
            SeriesData other = new SeriesData();
            other.setName("其他");
            other.setValue(new BigDecimal(targetTotalCount - targetCount));
            seriesDataList.add(other);
        }
        //计算占比
        fillPercent(seriesDataList, targetTotalCount);
        return this.postHandler(calcContext, seriesDataList);
    }

    protected void fillPercent(List<SeriesData> seriesDataList, int targetTotalCount) {
        BigDecimal targetCountBigDecimal = new BigDecimal(targetTotalCount);
        BigDecimal targetTotalPercent = new BigDecimal(0);
        for (int i = 0; i < seriesDataList.size(); i++) {
            SeriesData seriesData = seriesDataList.get(i);
            BigDecimal percent = seriesData.getValue().divide(targetCountBigDecimal, 3, RoundingMode.HALF_UP);
            targetTotalPercent = targetTotalPercent.add(percent);
            if (i == seriesDataList.size() && targetTotalPercent.compareTo(BigDecimal.ONE) != 0 && targetTotalPercent.compareTo(BigDecimal.ZERO) != 0) {
                percent = percent
                        .add(BigDecimal.ONE.subtract(targetTotalPercent))
                        .setScale(3, RoundingMode.HALF_UP);
            }
            seriesData.setPercent(percent);
        }
    }

    /**
     * 删除自动添加的其他分类数据
     *
     * @param seriesDataList
     */
    protected void removeLastOtherData(List<SeriesData> seriesDataList) {
        if (null != seriesDataList && seriesDataList.size() > 0) {
            int lastDataIndex = seriesDataList.size() - 1;
            SeriesData lastData = seriesDataList.get(lastDataIndex);
            if ("其他".equals(lastData.getName())) {
                seriesDataList.remove(lastDataIndex);
            }
        }
    }
}
