package com.lvdreamer.basic;

import org.junit.Test;
import sun.java2d.SunGraphicsEnvironment;

import java.awt.*;

public class GraphicFontTest {
    @Test
    public void getAllFonts() {
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
        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }
    }
}
