package com.lvdreamer.basic.date;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;

public class JavaDateTest {
    @Test
    public void dayOfWeekTest(){
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
        LocalDate today=LocalDate.now();
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(today);
        System.out.println(lastDay.atStartOfDay());
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli()/1000);
        System.out.println(firstDayOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(today.withDayOfMonth(30).atStartOfDay().toEpochSecond(ZoneOffset.of("+8")));


    }
    @Test
    public void longCompareInt(){
        System.out.println(Integer.valueOf(10)>Long.valueOf(2));

    }
}
