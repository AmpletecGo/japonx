package wzbsdb.self;

import com.alibaba.fastjson.JSONObject;
import wzbsdb.utils.HttpClientUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 *  斐讯库存查询
 * @author Surging
 * @create 2018/10/12
 * @since 1.0.0
 */
public class FeixunStock {

    public static final String BASE_URL = "https://mall.phicomm.com/index.php/openapi/goods/products/goods_id/";

    public static final List<Integer> GOODS_IDS = Arrays.asList(1,2,3,4,6,7,8,9,10,11,12,311);

    public static void main(String[] args) throws IOException {
        /*for (Integer goodsId : GOODS_IDS) {
            String res = HttpClientUtil.sendGet(BASE_URL + goodsId);
            JSONObject jsonObject = JSONObject.parseObject(res).getJSONObject("data").getJSONObject("product");
            String goods_id = jsonObject.getString("goods_id");
            String stock = jsonObject.getString("stock");
            String name = jsonObject.getString("name");
            System.out.println("goods_id: "+goods_id+ "\t\t" +name+ "\t\t库存:"+ stock);
        }*/
        for (int i = 0; i < 500; i++) {
            String res = HttpClientUtil.sendGet(BASE_URL + i);
            try {
                JSONObject jsonObject = JSONObject.parseObject(res).getJSONObject("data").getJSONObject("product");
                String goods_id = jsonObject.getString("goods_id");
                String stock = jsonObject.getString("stock");
                String name = jsonObject.getString("name");
                System.out.println("goods_id: "+goods_id+ "\t\t" +name+ "\t\t库存:"+ stock);
            } catch (Exception e) {
            }

        }
    }

    /*private static void urlTest() throws IOException {
        URL url = new URL(BASE_URL + T1);
        URLConnection uc = url.openConnection();
        //获取读入流
        InputStream in = uc.getInputStream();
        //放入缓存流
        InputStream raw = new BufferedInputStream(in);
        //最后使用Reader接收
        *//*Reader r = new InputStreamReader(raw);

        //打印输出
        int c;
        while((c = r.read())>0){
            System.out.print((char)c);
        }*//*
        *//*int c;
        while ((c = raw.read()) > 0){
            System.out.print((char)c);
        }*//*
    }*/
}
