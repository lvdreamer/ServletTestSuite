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

    private String localFileDir = "file:/data/dna/handfile/?delay=5s&move=backup/${date:now:yyyyMMdd}/${file:name}&moveFailed=error/${date:now:yyyyMMdd}/${file:name}";
    @Autowired
    private FileProcess fileProcess;

    @Override
    public void configure() throws Exception {

        from(localFileDir)    //数据源
                .log(LoggingLevel.INFO, logger, "process file ${file:name} start.")
                .process(fileProcess)//处理器
                .log(LoggingLevel.INFO, logger, "process file ${file:name} end.");

    }
}
