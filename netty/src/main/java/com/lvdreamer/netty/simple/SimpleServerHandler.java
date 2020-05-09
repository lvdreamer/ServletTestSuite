package com.lvdreamer.netty.simple;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(SimpleServerHandler.class);

    /**
     * 客户端连接后 启动一个线程读取命令行信息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        new Thread(() -> {
            Channel ch = ctx.channel();
            System.out.println("Please enter msg(use quit to exist):");
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = null;
                try {
                    line = in.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (line == null || "quit".equalsIgnoreCase(line)) {
                    break;
                }

                lastWriteFuture = ch.writeAndFlush(line + "\r\n");
            }
            if (lastWriteFuture != null) {
                lastWriteFuture.awaitUninterruptibly();
            }
        }).start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("receiver msg:{}", msg);
        ctx.write("I got msg:" + msg + "\r\n");

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
