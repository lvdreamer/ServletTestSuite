package com.lvdreamer.spring.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private final static Logger logger = LoggerFactory.getLogger(DataService.class);

    @Cacheable(cacheNames = {"dataService"}, key = "targetClass + methodName +#inStr")
    public String queryData(String inStr) {
        logger.info("未命中缓存，从服务获取数据");
        return "#Cache#" + inStr;
    }
}
