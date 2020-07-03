package com.lvdreamer.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFileMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;

@Component
public class FileProcess implements Processor {
    private static Logger logger = LoggerFactory.getLogger(FileProcess.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        GenericFileMessage<File> inFileMessage = (GenericFileMessage<File>) exchange.getIn();

        logger.info("文件名 ： " + inFileMessage.getHeader(Exchange.FILE_NAME));
        logger.info("绝对路径 ： " + inFileMessage.getHeader("CamelFileAbsolutePath"));
        logger.info("最后修改时间 ： " + inFileMessage.getHeader("CamelFileLastModified"));
        logger.info("文件大小： " + inFileMessage.getHeader("CamelFileLength"));
        File body = inFileMessage.getGenericFile().getFile();
        logger.info(String.valueOf(body instanceof File));
        if (exchange.getIn().getHeader(Exchange.FILE_NAME).equals("multi_1_2b47fef37d75408ba5292da8f3f3bc3e-宽表测试数据.txt")) {
            throw new RuntimeException("异常文件");
        }
    }

}
