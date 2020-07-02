package com.lvdreamr.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

public class CamelSimpleTest {

    @Test
    public void demo() throws Exception {
        //Camel 的上下文对象
        CamelContext context = new DefaultCamelContext();
        // 添加路由
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/data/camel/source?delay=5s")
                        .to("file:/data/camel/test/");
            }
        });
        //启动
        context.start();
        while(true) {

        }
    }
}
