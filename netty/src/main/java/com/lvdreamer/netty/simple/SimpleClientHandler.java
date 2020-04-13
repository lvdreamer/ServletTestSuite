package com.lvdreamer.netty.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

public class SimpleClientHandler extends SimpleChannelInboundHandler<Object> {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance(SimpleClientHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("receiver msg from server:{}", msg);
    }
}
