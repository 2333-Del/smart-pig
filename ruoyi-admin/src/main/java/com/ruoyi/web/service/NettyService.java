package com.ruoyi.web.service;

import com.ruoyi.web.netty.RtspHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.unix.PreferredDirectByteBufAllocator;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.cors.CorsConfig;
import io.netty.handler.codec.http.cors.CorsConfigBuilder;
import io.netty.handler.codec.http.cors.CorsHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NettyService {
    @Autowired
    private RtspHandler rtspHandler;

    @Value("${netty.port}")
    private Integer port;

    public void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(200);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            CorsConfig corsConfig = CorsConfigBuilder.forAnyOrigin().allowNullOrigin().allowCredentials().build();
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpResponseEncoder())
                                    .addLast(new HttpRequestDecoder())
                                    .addLast(new ChunkedWriteHandler())
                                    /**
                                     * http数据再传输中是分段的 HttpObjectAggregator ，就是可以将多个段聚合
                                     * 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                                     */
                                    .addLast(new HttpObjectAggregator(64*1024))
                                    /**
                                     * 1. 对应websocket ，它的数据是以 帧(frame) 形式传递
                                     * 2. 可以看到WebSocketFrame 下面有六个子类
                                     * 3. 浏览器请求时 ws://localhost:7000/hello 表示请求的uri
                                     * 4. WebSocketServerProtocolHandler 核心功能是将 http协议升级为 ws协议 , 保持长连接
                                     * 5. 是通过一个 状态码 101
                                     */
                                    .addLast(new CorsHandler(corsConfig))
                                    .addLast(rtspHandler);
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .option(ChannelOption.ALLOCATOR, PreferredDirectByteBufAllocator.DEFAULT)
                    .childOption(ChannelOption.TCP_NODELAY, true).childOption(ChannelOption.SO_KEEPALIVE, true).childOption(ChannelOption.SO_RCVBUF, 128 * 1024).childOption(ChannelOption.SO_SNDBUF, 1024 * 1024).childOption(ChannelOption.WRITE_BUFFER_WATER_MARK, new WriteBufferWaterMark(1024 * 1024 / 2, 1024 * 1024));
            ChannelFuture sync = bootstrap.bind(port).sync();
            log.info("netty启动成功监听端口号{}",port);
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
