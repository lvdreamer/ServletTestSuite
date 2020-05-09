package com.lvdreamer.spring.starter;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShowTimeBean {

    public void showNow() {
        System.out.println("The system start time is:" + LocalTime.now());
    }
}
