package com.lvdreamer.basic.date;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class JavaDateTest {
    @Test
    public void dayOfWeekTest() {
        System.out.println(LocalDate.now().getDayOfWeek().getValue());
        LocalDate today = LocalDate.now();
        System.out.println(today.toString());
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(today);
        System.out.println(lastDay.atStartOfDay());
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println(firstDayOfMonth.atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000);
        System.out.println(firstDayOfMonth.atStartOfDay().toEpochSecond(ZoneOffset.of("+8")));
        System.out.println(today.withDayOfMonth(30).atStartOfDay().toEpochSecond(ZoneOffset.of("+8")));


    }

    @Test
    public void longCompareInt() {
        System.out.println(Integer.valueOf(10) > Long.valueOf(2));

    }

    @Test
    public void dataFormat() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter hhmmssFormatter = DateTimeFormatter.ofPattern("HHmmss");

        LocalDateTime localDateTime = LocalDateTime.now();
        String yyyyMMdd = yyyyMMddFormatter.format(localDateTime);
        String hhmiss = hhmmssFormatter.format(localDateTime);
        String date = dateTimeFormatter.format(localDateTime);
        String yyyyMMdd2 = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String hhmiss2 = new SimpleDateFormat("HHmmss").format(new Date());
        String date2 = new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date());
        System.out.println(yyyyMMdd);
        System.out.println(hhmiss);
        System.out.println(date);
        System.out.println("=====================");
        System.out.println(yyyyMMdd2);
        System.out.println(hhmiss2);
        System.out.println(date2);
        System.out.println(yyyyMMdd2.equals(yyyyMMdd));
        System.out.println(hhmiss2.equals(hhmiss2));
        System.out.println(date2.equals(date2));
    }

    @Test
    public void timeMinus() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        Thread.sleep(1000L);
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(Duration.between(now, now2).toMillis());

    }

    @Test
    public void testTimeDup() throws InterruptedException { //保存上次文件时间，避免时间重复
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd HHmmss");
        DateTimeFormatter yyyyMMddFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter hhmmssFormatter = DateTimeFormatter.ofPattern("HHmmss");
        //保存上次文件时间，避免时间重复
        LocalDateTime localDateTime = null;
        for (int i = 0; i < 20; i++) {
            if (null != localDateTime) {
                // 判断是否到下一秒，如果不到就等待
                LocalDateTime nextDateTime = localDateTime.plusSeconds(1);
                long nextDateTimeMillis = nextDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
                while (System.currentTimeMillis() - nextDateTimeMillis <= 0) {
                    Thread.sleep(200);
                }
            }
            localDateTime = LocalDateTime.now();
            String yyyyMMdd = yyyyMMddFormatter.format(localDateTime);
            String hhmiss = hhmmssFormatter.format(localDateTime);
            String date = dateTimeFormatter.format(localDateTime);
            //这里判断和上次时间是否重复，重复需要+1
            System.out.println(yyyyMMdd + hhmiss);
        }

    }
}
