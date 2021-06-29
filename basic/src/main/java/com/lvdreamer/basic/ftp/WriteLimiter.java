package com.lvdreamer.basic.ftp;

import java.io.IOException;
import java.io.OutputStream;
/**
 * @author Le
 */
public class WriteLimiter extends OutputStream {
    private OutputStream os = null;
    private StreamLimiter bandwidthLimiter = null;

    public WriteLimiter(OutputStream os, StreamLimiter bandwidthLimiter) {
        this.os = os;
        this.bandwidthLimiter = bandwidthLimiter;
    }

    @Override
    public void write(int b) throws IOException {
        if (bandwidthLimiter != null)
            bandwidthLimiter.limitNextBytes();
        this.os.write(b);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        if (bandwidthLimiter != null)
            bandwidthLimiter.limitNextBytes(len);
        this.os.write(b, off, len);
    }

}