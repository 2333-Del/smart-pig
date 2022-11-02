package com.ruoyi.web.ffmpeg;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ConversionVideo implements ApplicationRunner {
    public Process process;

    public Integer pushVideoAsRTSP(String id, String fileName){
        int flag = -1;
        // ffmpeg位置，最好写在配置文件中
//          String ffmpegPath = "D:\\Program Files"+"\\ffmpeg-5.0.1-essentials_build\\bin";
        String ffmpegPath = System.getProperty("ffmpeg");
        try {
            // 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
            if(process != null){
                process.destroy();
                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
            }
            // cmd命令拼接，注意命令中存在空格
            String command = ffmpegPath;
            // ffmpeg开头，-re代表按照帧率发送，在推流时必须有
            //   command += "ffmpeg ";
            // 指定要推送的视频
            command += " -rtsp_transport tcp -i \"" + id + "\"";
            //rtsp协议默认使用udp导致的问题，所以rtsp强制使用tcp方式可以一定程度避免丢包

            // 指定推送服务器，-f：指定格式
            command += " -q 0 -f mpegts -codec:v mpeg1video -s 800x600 " + fileName;
            System.out.println("ffmpeg推流命令：" + command);
            // 运行cmd命令，获取其进程
            process = Runtime.getRuntime().exec(command);
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        String ip = "47.104.196.235:65488";
//        String url = "rtsp://admin:SYS11505@"+ip+":554/cam/realmonitor?channel=1&subtype=0";
        ConversionVideo conversionVideo = new ConversionVideo();
//        String rtsptest="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";
//        String rtspip="rtsp://admin:xinxi11505@10.1.1.66/cam/realmonitor?channel=1&subtype=0";
//        String rtspnew="rtsp://admin:SYS11505@192.168.10.222/cam/realmonitor?channel=1&subtype=1";
        String rtsptest="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";
        String rtspip="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";
        String rtspnew="rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mp4";
        conversionVideo.pushVideoAsRTSP(rtspnew, "http://127.0.0.1:8080/receive");
//        conversionVideo.pushVideoAsRTSP(url, "http://127.0.0.1:8080/receive");
    }

    //将ts码流转mp4
//    public Integer tstomp4(String filName,String ffmpeg){//filename:要
//        int flag = -1;
//        // ffmpeg位置，最好写在配置文件中
////          String ffmpegPath = "D:\\Program Files"+"\\ffmpeg-5.0.1-essentials_build\\bin";
//        String ffmpegPath = System.getProperty("ffmpeg");
//        try {
//            // 视频切换时，先销毁进程，全局变量Process process，方便进程销毁重启，即切换推流视频
//            if(process != null){
//                process.destroy();
//                System.out.println(">>>>>>>>>>推流视频切换<<<<<<<<<<");
//            }
//            // cmd命令拼接，注意命令中存在空格
//            String command = ffmpegPath;
//            // ffmpeg开头，-re代表按照帧率发送，在推流时必须有
//            //   command += "ffmpeg ";
//            // 指定要推送的视频
//            command += " -i \"" + id + "\"";
//            // 指定推送服务器，-f：指定格式
//            command += " -q 0 -f mpegts -codec:v mpeg1video -s 800x600 " + fileName;
//            System.out.println("ffmpeg推流命令：" + command);
//            // 运行cmd命令，获取其进程
//            process = Runtime.getRuntime().exec(command);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return flag;
//    }
}
