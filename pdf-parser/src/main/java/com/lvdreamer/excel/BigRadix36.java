package com.lvdreamer.excel;

import java.math.BigInteger;

/**
 * @author wanghb11
 * @version 1.0
 * @date 2020/7/10 17:47
 * @description 36进制
 **/
public class BigRadix36 {
    private static final int RADIX_36 = 36;

    public static BigInteger toInt(String radixNumStr) {
        return new BigInteger(radixNumStr, RADIX_36);
    }

    public static String toRadixStr(int num) {
        return new BigInteger(String.valueOf(num)).toString(RADIX_36);
    }

    public static String toRadixStr(BigInteger bigInteger) {
        return bigInteger.toString(RADIX_36);
    }
}
