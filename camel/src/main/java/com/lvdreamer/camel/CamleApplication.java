package com.lvdreamer.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamleApplication {
    public static void main(String[] args) {
        new SpringApplication(CamleApplication.class).run(args);
    }
}
