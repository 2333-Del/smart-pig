package com.ruoyi.web.task;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.pig.domain.JsonData;
import com.ruoyi.pig.domain.TbData;
import com.ruoyi.pig.domain.TbNewData;
import com.ruoyi.pig.service.ITbDataService;
import com.ruoyi.pig.service.ITbNewDataService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import sun.net.www.http.HttpClient;

import javax.print.DocFlavor;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class HttpGetDataTask {

    @Autowired
    private ITbDataService tbDataService;

    @Autowired
    private ITbNewDataService tbNewDataService;

    // *创建默认的httpClient实例(CloseableHttpClient).
    static CloseableHttpClient httpclient = HttpClients.createDefault();



    @Scheduled(cron = "0 0/10 * * * ?")
    public void da() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Date date = new Date();
        HttpResponse response = null;
        Long time = (Long) date.getTime();
        String str = time.toString() + "deviceNoAMT22041216320011" + "qqzS77nREDO9hQEQCSiyOYbiDL11TVyM";
        //未加密数据
        //  *创建HttpGet请求
        URIBuilder uriBuilder = null;
        try {
            //生成url等
            uriBuilder = new URIBuilder("http://admin.yun-sense.com/v1/getDeviceData");
            uriBuilder.setParameter("deviceNo", String.valueOf("AMT22041216320011"))
                    .setParameter("timestamp", String.valueOf(time.toString()))
                    .setParameter("sign", DigestUtils.md5Hex(str).toUpperCase());
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            System.out.println(DigestUtils.md5Hex(str).toUpperCase());

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String content = EntityUtils.toString(httpEntity, "utf8");
                if (content != null) {
                    JSONObject jsonObject = JSONObject.parseObject(content);
                    JsonData jsonData = JSON.parseObject(jsonObject.get("data").toString(), JsonData.class);   //json对象转换成实体类
                    TbData tbData = new TbData();
                    tbData.setEquipmentId(jsonData.getDeviceNo());
                    tbData.setAcquisitionTime(jsonData.getSensors().get(0).getTime());
                    tbData.setTemperature(jsonData.getSensors().get(0).getValue());
                    tbData.setHumidity(jsonData.getSensors().get(1).getValue());
                    tbData.setPm25(jsonData.getSensors().get(2).getValue());
                    tbData.setPm10(jsonData.getSensors().get(3).getValue());
                    tbData.setCo2(jsonData.getSensors().get(4).getValue());
                    tbData.setCo(jsonData.getSensors().get(5).getValue());
                    tbData.setAmmonia(jsonData.getSensors().get(6).getValue());
                    tbData.setSulfHydr(jsonData.getSensors().get(7).getValue());
                    tbDataService.insertTbData(tbData);  //插入数据
                    Integer id  = tbNewDataService.selectTbNewDataByRqId(tbData.getEquipmentId());
                    if (id!=null){
                        log.info("数据库修改数据");
                        TbNewData tbNewData = new TbNewData(tbData);
                        tbNewData.setId(Long.valueOf(id));
                        tbNewDataService.updateTbNewData(tbNewData);
                    } else {
                        log.info("数据库插入数据");
                        TbNewData tbNewData = new TbNewData(tbData);
                        tbNewDataService.insertTbNewData(tbNewData);
                    }
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
