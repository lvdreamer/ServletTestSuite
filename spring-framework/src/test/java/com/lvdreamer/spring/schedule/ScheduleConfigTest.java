package com.lvdreamer.spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@ContextConfiguration(classes = {
        ScheduleConfig.class,
})
public class ScheduleConfigTest {

    @Test
    public void doNothing() throws InterruptedException {
        TimeUnit.SECONDS.sleep(60L);
    }

}