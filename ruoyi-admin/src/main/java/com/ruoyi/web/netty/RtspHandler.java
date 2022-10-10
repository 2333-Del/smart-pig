package com.ruoyi.web.netty;

import com.ruoyi.web.controller.pig.CameraController;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Sharable
public class RtspHandler extends SimpleChannelInboundHandler<Object> {
    @Autowired
    private CameraController cameraController;

    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest){
            FullHttpRequest req = (FullHttpRequest) msg;
            QueryStringDecoder decoder = new QueryStringDecoder(req.uri());
            if (!"/live".equals(decoder.path())) {
                System.err.println("uri有误");
                sendError(ctx, HttpResponseStatus.BAD_REQUEST);
                return;
            }
            if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))){
                // http请求
                log.info("HTTP请求");
                sendFlvReqHeader(ctx);
                //   playServer.playForHttp(ctx,ClientType.HTTP);
            }else {
                // websocket握手，请求升级
                // 参数分别是ws地址，子协议，是否扩展，最大frame长度
                WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(getWebSocketLocation(req), "null", true, 5 * 1024 * 1024);
                handshaker = factory.newHandshaker(req);
                if (handshaker == null) {
                    WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                } else {
                    handshaker.handshake(ctx.channel(), req);
                    // todo rstp->ppfmge->http->ws
                    cameraController.setWsClients(ctx);
                }
            }

        } else if (msg instanceof WebSocketFrame) {
            handleWebSocketRequest(ctx, (WebSocketFrame) msg);
        }
        System.err.println("发送消息走完");
    }

    private String getWebSocketLocation(FullHttpRequest request) {
        String location = request.headers().get(HttpHeaderNames.HOST) + request.uri();
        System.out.println("ws://" + location);//ws://localhost:8866/live
        return "ws://" + location;
    }

    private void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        System.err.println("请求地址有错误");
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status,
                Unpooled.copiedBuffer("请求地址有误: " + status + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * websocket处理
     *
     * @param ctx
     * @param frame
     */
    private void handleWebSocketRequest(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
        // 关闭
        if (frame instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 握手PING/PONG
        if (frame instanceof PingWebSocketFrame) {
            ctx.write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 文本
        if (frame instanceof TextWebSocketFrame) {
            frame.retain(1);
            ctx.channel().writeAndFlush(new TextWebSocketFrame(((TextWebSocketFrame) frame).text()));
            return;
        }
        if (frame instanceof BinaryWebSocketFrame) {
            return;
        }
    }

    /**
     * 发送req header，告知浏览器是flv格式
     * @param ctx
     */
    private void sendFlvReqHeader(ChannelHandlerContext ctx) {
        HttpResponse rsp = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        rsp.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE)
                .set(HttpHeaderNames.CONTENT_TYPE, "video/x-flv").set(HttpHeaderNames.ACCEPT_RANGES, "bytes")
                .set(HttpHeaderNames.PRAGMA, "no-cache").set(HttpHeaderNames.CACHE_CONTROL, "no-cache")
                .set(HttpHeaderNames.TRANSFER_ENCODING, HttpHeaderValues.CHUNKED).set(HttpHeaderNames.SERVER, "zhang");
        ctx.writeAndFlush(rsp);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("web客户端被链接"+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生 " + cause.getMessage());
        ctx.close(); //关闭连接
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved 被调用" + ctx.channel().id().asLongText());
    }
}
