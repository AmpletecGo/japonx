package wzbsdb.self;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import wzbsdb.utils.HttpClientUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 〈一句话功能简述〉<br>
 *  斐讯库存查询
 * @author Surging
 * @create 2018/10/12
 * @since 1.0.0
 */
public class FeixunStock {

    public static final String BASE_URL = "https://mall.phicomm.com/index.php/openapi/goods/products/goods_id/";

    public static final Integer T1 = 12;

    public static void main(String[] args) throws IOException {
        String res = HttpClientUtil.sendGet(BASE_URL + T1);
        JSONObject jsonObject = JSON.parseObject(res);
        String id = jsonObject.getString("data");
        System.out.println(jsonObject);
    }

    private static void urlTest() throws IOException {
        URL url = new URL(BASE_URL + T1);
        URLConnection uc = url.openConnection();
        //获取读入流
        InputStream in = uc.getInputStream();
        //放入缓存流
        InputStream raw = new BufferedInputStream(in);
        //最后使用Reader接收
        /*Reader r = new InputStreamReader(raw);

        //打印输出
        int c;
        while((c = r.read())>0){
            System.out.print((char)c);
        }*/
        /*int c;
        while ((c = raw.read()) > 0){
            System.out.print((char)c);
        }*/
    }
}
