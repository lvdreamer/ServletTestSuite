package com.lvdreamer.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileProcess implements Processor {
    private static Logger logger = LoggerFactory.getLogger(FileProcess.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("文件名 ： " + exchange.getIn().getHeader(Exchange.FILE_NAME));
        logger.info("绝对路径 ： " + exchange.getIn().getHeader("CamelFileAbsolutePath"));
        logger.info("最后修改时间 ： " + exchange.getIn().getHeader("CamelFileLastModified"));
        logger.info("文件大小： " + exchange.getIn().getHeader("CamelFileLength"));
        Object body = exchange.getIn().getBody();
        logger.info(String.valueOf(body instanceof File));
        if (exchange.getIn().getHeader(Exchange.FILE_NAME).equals("multi_1_2b47fef37d75408ba5292da8f3f3bc3e-宽表测试数据.txt")) {
            throw new RuntimeException("异常文件");
        }
    }

}
