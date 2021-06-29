package com.lvdreamer.basic;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class IdGenTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(IdGenTest.class);

    @Test
    public void testProductIdByMoreThread() throws InterruptedException {
        List<Thread> tlist = new ArrayList<>();
        Set<Long> setAll = new HashSet<>();
        CountDownLatch cdLatch = new CountDownLatch(10);
        long start = System.currentTimeMillis();
        int n = 1000;
        IdGen idGen = new IdGen();
        for (int i = 0; i < 10; i++) {
            Thread temp = new Thread(new Runnable() {
                @Override
                public void run() {
                    Set<Long> setId = new HashSet<>();
                    for (int j = 0; j < n; j++) {
                        setId.add(idGen.nextId());
                    }
                    synchronized (setAll) {
                        setAll.addAll(setId);
                        LOGGER.info("{}生产了{}个id,并成功加入到setAll中.", Thread.currentThread().getName(), n);
                    }
                    cdLatch.countDown();
                }
            }, "snowflake" + i);
            tlist.add(temp);
        }
        for (int j = 0; j < 10; j++) {
            tlist.get(j).start();
        }
        cdLatch.await();

        long end1 = System.currentTimeMillis() - start;

        LOGGER.info("共耗时:{}毫秒,预期应该生产{}个id, 实际合并总计生成ID个数:{}", end1, 10 * n, setAll.size());

    }
}