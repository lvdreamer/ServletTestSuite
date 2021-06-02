package com.lvdreamer.behavior.strategy.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataCalculatoerLoader {
    private static final Logger logger = LoggerFactory.getLogger(DataCalculatoerLoader.class);

    private static final SeriesDataCalculator donothingDataCalculator = new SeriesDataCalculator() {
        @Override
        public CalcContext preHandler(CalcContext calcContext) {
            return calcContext;
        }

        @Override
        public List<SeriesData> calcSeriesData(CalcContext calcContext) {
            logger.error("获取不到预制数据计算器，直接返回空数据");
            return Collections.emptyList();
        }

        @Override
        public List<SeriesData> postHandler(CalcContext calcContext, List<SeriesData> seriesDataList) {
            return seriesDataList;
        }
    };

    private DataCalculatoerLoader() {

    }


    private static volatile ConcurrentHashMap<String, SeriesDataCalculator> dataCalculatorMap = new ConcurrentHashMap<>();

    public static void register(String seriesType, SeriesDataCalculator dataCalculator) {
        dataCalculatorMap.put(seriesType, dataCalculator);
    }

    public static SeriesDataCalculator getDataCalculator(String type) {
        return dataCalculatorMap.getOrDefault(type, donothingDataCalculator);
    }
}
