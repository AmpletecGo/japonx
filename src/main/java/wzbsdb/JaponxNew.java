package wzbsdb;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wzbsdb.utils.HtmlunitUtils;

public class JaponxNew {

    private static final String BASE_URL = "https://www.japonx.net";

    private static final String SUB_URL = "/portal/index/search/zimu_id/35.html";

    public static void main(String[] args) {
        HtmlunitUtils httpUtils = HtmlunitUtils.getInstance();
        httpUtils.setTimeout(30000);
        httpUtils.setWaitForBackgroundJavaScript(30000);
        //获取请求连接
        Connection con = Jsoup.connect(BASE_URL);
        //请求头设置，特别是cookie设置
        con.header("Accept", "text/html, application/xhtml+xml, */*");
        con.header("Content-Type", "application/x-www-form-urlencoded");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
        try {
            //Document document = httpUtils.getHtmlPageResponseAsDocument(BASE_URL+SUB_URL);
            Document document = con.get();
            Element element = document.getElementById("works");
            Elements li = element.getElementsByTag("li");
            li.stream().forEach(e -> {
                // 演员
                String videoStaring = e.getElementsByTag("p").text();
                // 封面图
                String videoImage = e.getElementsByTag("img").attr("src");
                // 详细页地址
                String detailLink = BASE_URL + e.getElementsByTag("a").first().attr("href");
                try {
                    //Document detailElement = Jsoup.connect(videoLink).get();
                    //System.out.println(detailElement);
                    Document detailElement = httpUtils.getHtmlPageResponseAsDocument(detailLink);
                    // 影片名称
                    String videoName = detailElement.title();
                    // 简介
                    String description = detailElement.select("meta[name=description]").get(0).attr("content");
                    // 番号
                    String designation = detailElement.getElementsByClass("no-hover").get(0).text();
                    String runtime = detailElement.getElementsByClass("no-hover").get(1).text();
                    // 字幕
                    //System.out.println(detailElement);
                    Elements elements = detailElement.getElementsByClass("dplayer-video-wrap").first().getElementsByTag("video");
                    String videoUrl = elements.attr("src");
                    String subtitleUrl = elements.first().getElementsByTag("track").attr("src");
                    System.out.println(videoUrl + subtitleUrl);
                    //System.out.println(videoName +"\t" +designation +"\t" +runtime +"\t" +description +"\n");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //System.out.println(videoStaring+"        "+videoLink +"        "+videoImage);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
