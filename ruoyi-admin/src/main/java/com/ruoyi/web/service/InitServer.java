package com.ruoyi.web.service;

import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacpp.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * netty 启动类
 */
@Slf4j
@Component
public class InitServer  implements CommandLineRunner {
    @Autowired
    private NettyService nettyServer;

    @Override
    public void run(String... args) throws Exception {
        log.info("启动netty服务器");
        nettyServer.start();
    }

    /**
     * 提前初始化，可避免推拉流启动耗时太久
     */
    @PostConstruct
    public void loadFFmpeg() {
        log.info("正在初始化资源，请稍等...");
        String ffmpeg = Loader.load(org.bytedeco.ffmpeg.ffmpeg.class);
        System.out.println(ffmpeg);

        String path = ffmpeg.substring(0,ffmpeg.indexOf(".exe"));
        System.setProperty("ffmpeg",path);

//        String linuxpath=ffmpeg;
//        System.setProperty("ffmpeg",linuxpath);
        log.info(System.getProperty("ffmpeg"));
        log.info("初始化成功");
    }
}
