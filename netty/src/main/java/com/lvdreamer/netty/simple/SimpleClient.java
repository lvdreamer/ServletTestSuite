package com.lvdreamer.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleClient {


    static final String HOST = "127.0.0.1";
    static final Integer PORT = 8009;

    public static void main(String[] args) throws Exception {

        EventLoopGroup clientGroup = new NioEventLoopGroup();
        try {

            Bootstrap cb = new Bootstrap();
            cb.group(clientGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                                 @Override
                                 protected void initChannel(SocketChannel sc) throws Exception {
                                     ChannelPipeline pipeline = sc.pipeline();
                                     pipeline.addLast(new LoggingHandler(LogLevel.INFO));
                                     pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                                     pipeline.addLast("decoder", new StringDecoder());
                                     pipeline.addLast("encoder", new StringEncoder());
                                     pipeline.addLast(new SimpleClientHandler());
                                 }
                             }
                    );

            Channel ch = cb.connect(HOST, PORT).sync().channel();

            System.out.println("Please enter msg(use quit to exist):");
            ChannelFuture lastWriteFuture = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = in.readLine();
                if (line == null || "quit".equalsIgnoreCase(line)) {
                    break;
                }

                lastWriteFuture = ch.writeAndFlush(line + "\r\n");
            }
            if (lastWriteFuture != null) {
                lastWriteFuture.awaitUninterruptibly();
            }
        } finally {
            clientGroup.shutdownGracefully();
        }
    }
}
