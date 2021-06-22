package com.lvdreamer.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
/**
 * @author wanghb11
 * @version 1.0
 * @date 2021/6/22 10:54
 * @description 正态分布随机数
 **/
public class GaussianDataTest {

    @Test
    public void genData() {
        float n = 8000f;
        int a = 150;
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < 10; i++) {
            double result= Math.sqrt(n) * random.nextGaussian() + a;
            System.out.println(result<0?0:(int)result);
        }
        System.out.println(randomUserCount(5000,2));
        System.out.println("================================");
        System.out.println(randomUserCount(5000,10));
    }

    public List<Integer> randomUserCount(int total, int cnt) {
        List<Integer> tmp = new ArrayList<>();
        int tmptotal = 0;
        for (int i = 0; i < cnt; i++) {
            int count = randomUserCount();
            tmp.add(count);
            tmptotal = tmptotal + count;
        }
        double radio = total / tmptotal;
        List<Integer> finalData=new ArrayList<>();
        for (Integer d : tmp) {
            finalData.add((int) (d * radio));
        }
        return finalData;
    }

    public int randomUserCount() {
        float n = 8000f;
        int a = 150;
        java.util.Random random = new java.util.Random();
        double result = Math.sqrt(n) * random.nextGaussian() + a;
        return result < 0 ? 0 : (int) result;
    }
}
