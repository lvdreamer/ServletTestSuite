package com.lvdreamer.basic;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class StringTest {

    @Test
    public void cutString() {
        System.out.println(StringUtils.abbreviate(null, 4));
        System.out.println(StringUtils.abbreviate("abcd", 6));
        System.out.println(StringUtils.abbreviate("abcdefg", 7));
        System.out.println(StringUtils.abbreviate("abcdefg", 8));
        System.out.println(StringUtils.abbreviate("abcdefg", 4));
        System.out.println(StringUtils.abbreviate("abcdefgaaaaaaaaaaaaaaaaaaaaaaaaa", 20));
    }
}
