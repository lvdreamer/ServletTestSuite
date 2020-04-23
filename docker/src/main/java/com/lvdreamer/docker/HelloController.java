package com.lvdreamer.docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/index")
    public String sayHello() {
        return "hello docker";
    }
}
