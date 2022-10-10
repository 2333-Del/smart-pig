package com.ruoyi.web.controller.pig;

import com.ruoyi.web.Util.FileUploadUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 监控controller 用于调取监控
 */
@Controller
public class CameraController {
    private ConcurrentHashMap<String, ChannelHandlerContext> wsClients = new ConcurrentHashMap<>();

    @RequestMapping("/receive")
    @ResponseBody
    public String receive(HttpServletRequest request) {
        try {
            ServletInputStream inputStream = request.getInputStream();
            int len = -1;
            while ((len =inputStream.available()) !=-1) {
                byte[] data = new byte[len];
                inputStream.read(data);
                sendVideo(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("over");
        return "1";
    }

    public void sendVideo(byte[] data) {
        // ws
        for (Map.Entry<String, ChannelHandlerContext> entry : wsClients.entrySet()) {
            try {
                if (entry.getValue().channel().isWritable()) {
                    entry.getValue().writeAndFlush(new BinaryWebSocketFrame(Unpooled.copiedBuffer(data)));
                } else {
                    wsClients.remove(entry.getKey());
                }
            } catch (Exception e) {
                wsClients.remove(entry.getKey());
                e.printStackTrace();
            }
        }
    }

    public void setWsClients(ChannelHandlerContext ctx) {
        wsClients.put(ctx.channel().id().asLongText(),ctx);
    }


    @RequestMapping(path="/download/{bzlobstring}",method = RequestMethod.POST)
    @ResponseBody
    public void download(HttpServletResponse response,@RequestParam("file") MultipartFile blobFile) {
        response.addHeader("Access-Control-Allow-Origin", "*");//允许所有来源访同

        String name=new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss")
                .format(new Date()).replace("/","")+".ts";

        FileUploadUtil fileUploadUtil = new FileUploadUtil();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取电脑 用户名
        String path="C:\\Users\\"+userName+"\\Desktop\\";
        String fileNamePath=path+name;
        if(fileUploadUtil.uploadBlobImgFile(blobFile,fileNamePath)){
            System.out.println("success");
        }else{
            System.out.println("error");
        }
    }
}
