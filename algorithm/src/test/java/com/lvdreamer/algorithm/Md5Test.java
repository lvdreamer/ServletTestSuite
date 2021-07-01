package com.lvdreamer.algorithm;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class Md5Test {

    /**
     * md5编码字符串
     */
    @Test
    public void md5Encode() throws InterruptedException {
        System.out.println(DigestUtils.md5DigestAsHex("abc".getBytes()));

        for (int i = 0; i < 10; i++) {
            System.out.println(DigestUtils.md5DigestAsHex(("abc" + System.currentTimeMillis()).getBytes()));
            Thread.sleep(1L);

        }

    }
}
