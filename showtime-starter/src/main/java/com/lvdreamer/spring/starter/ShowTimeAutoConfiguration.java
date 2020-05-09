package com.lvdreamer.spring.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShowTimeAutoConfiguration {
    @Bean
    public ShowTimeBean init() {
        return new ShowTimeBean();
    }
}
