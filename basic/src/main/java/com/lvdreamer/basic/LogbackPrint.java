package com.lvdreamer.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogbackPrint {
    private static final Logger log = LoggerFactory.getLogger(LogbackPrint.class);

    public static void main(String[] args) {
        log.trace("trace-trace-trace");
        log.debug("debug-debug-debug");
        log.info("info-info-info");
        log.warn("warn-warn-warn");
        log.error("error-error-error");
    }
}
