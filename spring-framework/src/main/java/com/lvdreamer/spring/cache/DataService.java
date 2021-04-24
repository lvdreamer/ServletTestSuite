package com.lvdreamer.spring.cache;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    @Cacheable(key = "targetClass + methodName +#inStr")
    public String queryData(String inStr) {

        return "#Cache#" + inStr;
    }
}
