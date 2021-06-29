package com.lvdreamer.basic.math;

import org.junit.Test;
import sun.java2d.SunGraphicsEnvironment;

import java.awt.*;

public class IntTest {

    @Test
    public void hexToNum() {
        System.out.println(Integer.valueOf("9fa5", 16));
    }

    @Test
    public void jianjian() {
        Integer s = 5;
        while (--s > 0) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
        Font[] fonts = new SunGraphicsEnvironment() {
            @Override
            protected int getNumScreens() {
                return 0;
            }

            @Override
            protected GraphicsDevice makeScreenDevice(int i) {
                return null;
            }

            @Override
            public boolean isDisplayLocal() {
                return false;
            }
        }.getAllFonts();
        for(int i=0;i<fonts.length;i++){
            System.out.println(fonts[i]);
        }
    }
}
