package com.lvdreamer.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode No.625
 * 给定一个正整数a，找到最小的正整数b，它的每个数字相乘之后等于a。
 * <p>
 * 如果没有答案，或者答案超过了32位有符号整型的范围，返回0。
 */
public class MinimumFactorization {

    public static void main(String[] args) {

        //   System.out.println(getMininumFactorization(48));
        //   System.out.println(getMininumFactorization(15));
        System.out.println(smallestFactorization2(81));

    }

    public static int smallestFactorization2(int n) {
        if (n < 10) return n;

        List<Integer> res = new ArrayList<>();
        for (int i = 9; i > 1; i--) {

            /**
             * 如果可以被i整除，对除数继续循环
             */
            while (n % i == 0) {
                n = n / i;
                res.add(i);
            }
        }

        /**
         * 没除尽，表示没有最小因子
         */
        if (n != 1) return 0;

        //最小数子
        long result = 0;
        for (int i = res.size() - 1; i >= 0; i--) {
            result = result * 10 + res.get(i);
            if (result > Integer.MAX_VALUE) return 0;
        }

        return (int) result;
    }
}
