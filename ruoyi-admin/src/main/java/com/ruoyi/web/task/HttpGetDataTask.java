package com.ruoyi.web.task;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import sun.net.www.http.HttpClient;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class HttpGetDataTask {

    // *创建默认的httpClient实例(CloseableHttpClient).
    static CloseableHttpClient httpclient = HttpClients.createDefault();


    public void da() {
        Date date = new Date();
        Long time = (Long) date.getTime();
        System.out.println(time);

        String str = time.toString() + "deviceNoAMT22041216320011" + "qqzS77nREDO9hQEQCSiyOYbiDL11TVyM";
        DigestUtils.md5Hex(str);

        //  *创建HttpGet请求
        HttpGet httpGet = new HttpGet("http://admin.sensor-monitor.cn/v1/getDeviceData");

        //  *执行get请求
        //  *执行get请求
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpGet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
