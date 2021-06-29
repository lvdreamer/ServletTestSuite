package com.lvdreamer.spring.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CacheConfigTest {
    @Autowired
    private DataService dataService;
    private int index = 1;

    @Test
    public void testLoadData() {
        System.out.println(dataService.queryData("aaa"));
        System.out.println(dataService.queryData("aaa"));
        System.out.println(dataService.queryData("aaa"));

    }

    /**
     * 模拟从数据库中读取数据
     *
     * @return
     */
    private int getInDB() {
        // 这里为了体现数据重新被get，因而用了index++
        index++;
        System.out.println("load data:" + index);
        return index;
    }

    @Test
    public void test() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(2, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, Integer>() {
                    @Nullable
                    @Override
                    public Integer load(@NonNull Integer key) {
                        return getInDB();
                    }
                });
        cache.put(1, getInDB());

        System.out.println(cache.getIfPresent(1));
        // 休眠2.1秒，后取值
        Thread.sleep(2100);
        System.out.println(cache.getIfPresent(1));
    }
}