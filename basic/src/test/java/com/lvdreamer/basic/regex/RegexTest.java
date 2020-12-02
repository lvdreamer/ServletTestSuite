package com.lvdreamer.basic.regex;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexTest {

    @Test
    public void testFileNameMatch() {
        System.out.println(Pattern.matches("(\\d)+_[Y|N]_(\\d)+", "222_Y_334"));
        System.out.println(Pattern.matches("(\\d)+_[Y|N]_(\\d)+", "multi_2_2b47fef37d75408ba5292da8f3f3bc3e-宽表测试数据"));

    }

    @Test
    public void testFileNameMatc2() {
        String fileName = "1009-Y_77.txt";
        String key = fileName.split("\\.", -1)[0].split("_", -1)[0];
        String columnNum = key.split("-", -1)[0];
        System.out.println(key);
        System.out.println(columnNum);
        System.out.println((char) 0x01);
    }

    @Test
    public void testContentSplit() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                this.getClass().getClassLoader().getResourceAsStream("Char01SplitFile"), "utf-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                System.out.println(Arrays.asList(line.split((new String(new byte[]{0x01})))));
                System.out.println(Arrays.asList(line.split("\\x01")));

            }
        }
    }

    @Test
    public void testCalcMemeryUse() {
        System.out.println(10000000 * ("U2FsdGVkX1/AnRcGSf1O5iluwJuptaD1kuTMwictSHQ=".getBytes().length) / 1024 / 1024);
    }

    @Test
    public void testFileNameGen() {
        String curDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(curDate);

        int random = ThreadLocalRandom.current().ints(1000, 9999).limit(1).findFirst().getAsInt();
        System.out.println(random);

        random = ThreadLocalRandom.current().ints(1000, 9999).limit(1).findFirst().getAsInt();
        System.out.println(random);

        random = ThreadLocalRandom.current().ints(1000, 9999).limit(1).findFirst().getAsInt();
        System.out.println(random);

        System.out.println(curDate + random);
    }

    @Test
    public void aaa() {
        List<String> abd = new ArrayList<>();
        //abd.add("a");
        //abd.add("b");
        Set<String> oldStringValueSet = abd.stream().map(Function.identity()).collect(Collectors.toSet());
        System.out.println(oldStringValueSet);
        String maxResult = oldStringValueSet.stream().max(String::compareTo).orElse(null);
        System.out.println("max:" + maxResult);
        Set<String> columnSet = new HashSet<>();
        columnSet.addAll(Arrays.asList(new String[]{"a", "b", "c"}));
        Sets.SetView<String> toAddStringValue = Sets.difference(columnSet, oldStringValueSet);
        System.out.println(toAddStringValue);
    }

    @Test
    public void tttt() {
        int start = 0x4e00;
        int end = 0x9fa5;
        for (int i = 0; i < 10; i++) {
            System.out.println(String.valueOf((char) (start + i)));

        }

    }

    public void j666(String column, Set<String> columnValueSet) {
        List<String> results = new ArrayList<>();
        List<String> avlResult = new ArrayList<>();
        String values = "4e00-9fa5";
        String[] split2 = values.split(",");
        for (String s : split2) {
            String[] datas = s.split("-");
            int start = Integer.valueOf(datas[0], 16).intValue();
            int end = Integer.valueOf(datas[1], 16).intValue();
            for (int i = start; i < end + 1; i++) {
                avlResult.add(String.valueOf((char) i));
            }
        }
        for (String value : columnValueSet) {
            Map columnValueMap = new HashMap();
            columnValueMap.put("otherKey", value);
            for (String result : avlResult) {
                if (!results.contains(result)) {
                    columnValueMap.put("result", result);
                    results.add(result);
                    break;
                }
            }
            System.out.println("insert model" + columnValueMap);
        }
    }

    @Test
    public void testJ777() {

    }

    public void j7777(String column, Set<String> columnValueSet) {
        int start = 0x4e00;
        int end = 0x9fa5;
        for (String value : columnValueSet) {
            if (start > end) {
                break;
            }
            Map columnValueMap = new HashMap();
            columnValueMap.put("otherKey", value);
            columnValueMap.put("result", String.valueOf((char) (start++)));
            System.out.println("insert model" + columnValueMap);
        }
    }

    @Test
    public void j999() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(new java.math.BigInteger(i + "", 10).toString(36));
        }
        System.out.println(new BigInteger("2v", 32));
        System.out.println("a".compareTo("100"));
        System.out.println(new BigInteger("a", 32).compareTo((new BigInteger("100", 32))));
    }

    @Test
    public void j888() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = LocalDate.now().atTime(LocalDateTime.now().getHour(), now.getMinute() >= 30 ? 30 : 0, 0);

        String startTimeStr = df.format(startTime);
        System.out.println("localTime = " + startTimeStr);

    }

    @Test
    public void j777888() {
        String downFileName = "12321-455";
        System.out.println(downFileName.substring(downFileName.indexOf("-") + 1));
    }

    @Test
    public void xxdd() {
        HashBasedTable<String, String, String> result = HashBasedTable.create();
        result.put("type", "a", "adwadw");
        result.put("type", "b", "adwadw");

        result.put("maile", "a", "wdadw");
        String value = result.get("type", "a");
        Map<String, String> rowGetKv = result.row("type");
        Map<String, String> columnGetKv = result.column("a");
        Map<String, Map<String, String>> columnMap = result.columnMap();
        Map<String, Map<String, String>> rowMap = result.rowMap();
        System.out.println("value结果为---" + value);
        System.out.println("rowGetKv结果为---" + rowGetKv);
        System.out.println("columnGetKv结果为---" + columnGetKv);
        System.out.println("columnMap结果为---" + columnMap);
        System.out.println("rowMap结果为---" + rowMap);
    }

    @Test
    public void xxdddd() {
        ImmutableTable<String, String, String> result = ImmutableTable.<String, String, String>builder()
                .put("type", "a", "手工导入")
                .put("type", "b", "adwadw")
                .put("maile", "a", "wdadw")
                .build();
        String value = result.get("type", "a");
        Map<String, String> rowGetKv = result.row("type");
        Map<String, String> columnGetKv = result.column("a");
        Map<String, Map<String, String>> columnMap = result.columnMap();
        Map<String, Map<String, String>> rowMap = result.rowMap();
        System.out.println("value结果为---" + value);
        System.out.println("rowGetKv结果为---" + rowGetKv);
        System.out.println("columnGetKv结果为---" + columnGetKv);
        System.out.println("columnMap结果为---" + columnMap);
        System.out.println("rowMap结果为---" + rowMap);
    }

    @Test
    public void fff() {
        System.out.println(null == (String) null);
    }


    @Test
    public void testDateFormateYYYYMM() {
        String orignalValue = "20010202";
        System.out.println(orignalValue.substring(0, 4) + "年" + orignalValue.substring(4, 6) + "月");
    }

    @Test
    public void testDateMinus() {
        LocalDate now = LocalDate.now();// 2018/8/25
        LocalDate start = LocalDate.parse("2020-07-20", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        long monthDiff = ChronoUnit.MONTHS.between(start, now);
        System.out.println(monthDiff);
    }

    @Test
    public void testStreamAbs() {
        List<Integer> columnNumList = Arrays.asList(-1, -2, -3);
        System.out.println(columnNumList);

        columnNumList = columnNumList.stream().map(Math::abs).collect(Collectors.toList());
        System.out.println(columnNumList);
    }

    @Test
    public void testUit() {
        String a = "近三月消费-本月消费（元）" + (char) 1 + "END";
        System.out.println(a.split(String.valueOf((char) 1))[1]);
    }

    @Test
    public void readAndWrite() throws IOException {
        String filecontent_separator = new String(new byte[]{0x01});
        InputStreamReader isr = new InputStreamReader(new FileInputStream("D:\\02-company\\05-亚信科技\\02-广州DNA\\temp\\a_dnatable_column_define_type_20201120110816.data"), "UTF-8");
        try (BufferedReader br = new BufferedReader(isr)) {
            String line;
            while ((line = br.readLine()) != null) {
               // String[] data = StringUtils.split(line,filecontent_separator);
                String[] data = line.split(filecontent_separator, -1);
                System.out.println(Arrays.toString(data));
            }
        }
    }

}