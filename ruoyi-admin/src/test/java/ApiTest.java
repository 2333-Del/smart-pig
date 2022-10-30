import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

public class ApiTest {

    @Test
    public void test01(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Date date = new Date();
        HttpResponse response = null;
        Long time = (Long) date.getTime();
        String str = time.toString() + "deviceNoAMT22041216320011" + "qqzS77nREDO9hQEQCSiyOYbiDL11TVyM";    //未加密数据
        //  *创建HttpGet请求

        URIBuilder uriBuilder = null;
        try {
            //生成url等
            uriBuilder = new URIBuilder("http://admin.sensor-monitor.cn/v1/getDeviceData");
            uriBuilder.setParameter("deviceNo", String.valueOf("AMT22041216320011"))
                    .setParameter("timestamp", String.valueOf(time.toString()))
                    .setParameter("sign", DigestUtils.md5Hex(str));
            HttpGet httpGet = new HttpGet(uriBuilder.build());

            response = httpclient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                String content = EntityUtils.toString(httpEntity, "utf8");
                if (content != null) {
//                    return JSONObject.parseObject(content);
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
