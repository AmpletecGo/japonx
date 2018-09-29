package wzbsdb.self;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Feixun {

    public static void main(String[] args) {
        String name = "17092509262";
        String password = "19940107";

        // 全局请求设置
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();

        // 创建cookie store的本地实例
        CookieStore cookieStore = new BasicCookieStore();

        // 创建HttpClient上下文
        HttpClientContext context = HttpClientContext.create();
        context.setCookieStore(cookieStore);

        // 创建一个HttpClient
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig)
                .setDefaultCookieStore(cookieStore).build();

        CloseableHttpResponse res = null;

        // 创建本地的HTTP内容
        try {
            try {
                // 创建一个get请求用来获取必要的Cookie，如_xsrf信息
                HttpGet get = new HttpGet("https://mall.phicomm.com/index.html");

                res = httpClient.execute(get, context);
                // 获取常用Cookie,包括_xsrf信息
                System.out.println("访问斐讯获取的常规Cookie:===============");
                for (Cookie c : cookieStore.getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }
                res.close();

                // 构造post数据
                List<NameValuePair> valuePairs = new LinkedList<>();
                valuePairs.add(new BasicNameValuePair("uname", name));
                valuePairs.add(new BasicNameValuePair("password", password));
                /*valuePairs.add(new BasicNameValuePair("remember_me", "true"));*/
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
                entity.setContentType("application/x-www-form-urlencoded");

                // 创建一个post请求
                HttpPost post = new HttpPost("https://mall.phicomm.com/passport-post_login.html");
                // 注入post数据
                post.setEntity(entity);
                res = httpClient.execute(post, context);

                // 打印响应信息，查看是否登陆是否成功
                System.out.println("打印响应信息===========");
                //HttpClientUtils.printResponse(res);
                res.close();

                System.out.println("登陆成功后,新的Cookie:===============");
                for (Cookie c : context.getCookieStore().getCookies()) {
                    System.out.println(c.getName() + ": " + c.getValue());
                }

                // 构造一个新的get请求，用来测试登录是否成功
                for (int i = 0; i < 100; i++) {
                    HttpGet newGet = new HttpGet("https://mall.phicomm.com/checkout.html");
                    res = httpClient.execute(newGet, context);
                    String content = EntityUtils.toString(res.getEntity());
                    Document doc = Jsoup.parse(content);
                    Element a = doc.getElementsByClass("page-header").first();
                    System.out.println(a.text());
                    res.close();
                }
            } finally {
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
