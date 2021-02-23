package com.lvdreamer.basic.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 常用时间操作
 * java.time包内，有几个比较重要的组件，
 * Instant（时间戳）；
 * LocalDate（日期）；
 * LocalDate（时间）；
 * LocalDateTime（日期时间）；
 * ZonedDateTime（带有区域信息的日期时间，比如中国默认使用的是东八区）。
 * Period（如两个日期之间相差的天数）；
 * Druation（两个日期时间之间间隔的秒和纳秒）。
 */
public class DateOperator {
    public static void main(String[] args) {

        Instant now = Instant.now();
        System.out.println(now.toString());  // 2018-08-06T09:44:13.677Z  (utc时间格式，标准时间格式)
        System.out.println(ZoneId.systemDefault());  // Asia/Shanghai
        LocalDateTime localDateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());
        System.out.println(localDateTime); //2018-08-06T17:44:13.677

        // 获得当前日期
        LocalDate nowDate = LocalDate.now();
        System.out.println(nowDate);
        //获取当天0点时间
        System.out.println(nowDate.atStartOfDay());
        // 日期加上1天
        LocalDate localDate1day = nowDate.plusDays(1);
        System.out.println(localDate1day.toString());
        // 日期加上一周
        LocalDate localDate1week = nowDate.plusWeeks(1);
        System.out.println(localDate1week);
        // 计算当前年的第52天是几月几号
        System.out.println("今年的第52天 = " + nowDate.withDayOfYear(52));
        //获取当月第一天
        System.out.println("当月第一天 = " + nowDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("当月最后一天 = " + nowDate.with(TemporalAdjusters.lastDayOfMonth()));


        // 字符串转日期
        DateTimeFormatter strToDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate date = LocalDate.parse("2021-02-01 13:00:00", strToDateFormatter);
        System.out.println(date);
        LocalDateTime dateTime = LocalDateTime.parse("2021-02-01 13:00:00", strToDateFormatter);
        System.out.println(dateTime.toString());

        //格式化日期
        LocalDateTime localDateTime1 = LocalDateTime.now();
        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = dateToStrFormatter.format(localDateTime1);
        System.out.println(dateStr);

        //Period代表的是两个日期之间的天、月、年数差值，当然，我们也可以直接使用日期中的until方法来完成同样的效果。
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(1);

        Period period = Period.between(startDate, endDate);
        System.out.println("间隔的天数" + period.getDays());
        System.out.println("间隔的月数:" + period.getMonths());
        System.out.println("间隔的年数:" + period.getYears());

        // 直接使用日期类中的方法计算日期间隔
        long days = startDate.until(endDate, ChronoUnit.DAYS);
        System.out.println("间隔的天数:" + days);
        long weeks = startDate.until(endDate, ChronoUnit.WEEKS);
        System.out.println("间隔的周数:" + weeks);

        /*Duration表示的是两个日期时间间隔的秒以及纳秒数。*/
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusDays(1);
        Duration duration = Duration.between(start, end);
        System.out.println("间隔的秒数:" + duration.get(ChronoUnit.SECONDS));

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String dateTimeF = LocalDateTime.now().format(fmt); //将当前时间转换为  2017-10-19 10:25:36
        //将时间装换为long值  时间戳
        long currentTimeMilli = LocalDateTime.parse(dateTimeF, fmt).atZone(ZoneId.of("Asia/Shanghai")).toInstant().toEpochMilli();
        System.out.println("时间戳:" + currentTimeMilli);
        //将long转为格式化时间字符串
        String timeNow = fmt.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(currentTimeMilli), ZoneId.of("Asia/Shanghai")));
        System.out.println("时间:" + timeNow);

        //Date转LocalDate
        Date date11 = new Date();
        LocalDate localDate11 = date11.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate11);
        //LocalDate 转 Date
        LocalDateTime localDateTime22 = LocalDateTime.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime22.atZone(zoneId);
        Date dateType = Date.from(zdt.toInstant());
        System.out.println("LocalDateTime = " + localDateTime22);
        System.out.println("Date = " + dateType);
    }
}
