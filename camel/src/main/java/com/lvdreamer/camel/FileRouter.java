package com.lvdreamer.camel;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileRouter extends RouteBuilder {
    private static Logger logger = LoggerFactory.getLogger(FileRouter.class);

    private String localFileDir = "file:/data/dna/handfile?delay=1s&move=../backup/${date:now:yyyyMMdd}/${file:name}";
    @Autowired
    private FileProcess fileProcess;

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .handled(true)// 路由停止将错误信息返回给最初的消费者
                .log(LoggingLevel.ERROR, logger, "Exception occurred due: ${exception.message}")
                .to("file:/data/dna/error").end();
        from(localFileDir)    //数据源
                .log(LoggingLevel.INFO, logger, "Downloaded file ${file:name} complete.")
                .process(fileProcess); //处理器

    }
}
