package com.lvdreamer.basic;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

public class StringTest {
    /**
     * 字符串超过显示最大长度用...替换
     */
    @Test
    public void cutString() {
        System.out.println(StringUtils.abbreviate(null, 4));
        System.out.println(StringUtils.abbreviate("abcd", 6));
        System.out.println(StringUtils.abbreviate("abcdefg", 7));
        System.out.println(StringUtils.abbreviate("abcdefg", 8));
        System.out.println(StringUtils.abbreviate("abcdefg", 4));
        System.out.println(StringUtils.abbreviate("abcdefgaaaaaaaaaaaaaaaaaaaaaaaaa", 20));
    }

    /**
     * 文件名截取特定字段
     */
    @Test
    public void fileNameCutField() {
        String fileNmae = "NG3Code_SZ_20201013_0002_DNA204_N_0_3.csv";
        System.out.println(fileNmae.substring(8, 10));
        System.out.println(fileNmae.substring(11, 17));

    }

    /**
     * 号码隐藏
     */
    @Test
    public void phoneMask() {
        System.out.println("13700020001".replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
        System.out.println(StringUtils.joinWith(",", "a", null, "", "bcd"));
    }

    /**
     * 字符串拼接
     */
    @Test
    public void testJoin() {
        System.out.println(Joiner.on("|").join(Arrays.asList("a", "b", "c")));
        System.out.println(StringUtils.joinWith("|", "a", null, "", "bcd"));

    }


    @Test
    public void jsonf() {
        String json = "{\"a\":10}";
        Map<String, Object> a = new HashMap<>();
        a.put("h1", "h1");
        a.put("a1", json);
        a.put("a2", JSON.parseObject(json));
        System.out.println(JSON.toJSONString(a));
    }

    @Test
    public void testSord() {
        List<Integer> data = Arrays.asList(1, 3, 5, 11, 3, 23, 44, 223, 32);
        data = data.stream().sorted()
                .limit(50)
                .collect(Collectors.toList());
        System.out.println(data);
    }

    /**
     * 获取最新时间的文件
     */
    @Test
    public void fileNameSortAndGetFirseTest() {
        List<String> fileList = Arrays.asList("fname_20210603.avl", "fname_20210602.avl","FNAME_20210608.avl");
        String lastFileName = fileList.stream().filter(f -> f.toLowerCase().contains("fname_".toLowerCase()) && (f.toLowerCase().endsWith(".avl"))).max(Comparator.comparing(String::toUpperCase)).orElse(null);
        System.out.println(lastFileName);
    }
}
