package wzbsdb;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wzbsdb.utils.DownLoadUtils;
import wzbsdb.utils.JsonUtils;
import wzbsdb.utils.JsUtils;
import wzbsdb.utils.RegexUtils;

import javax.script.ScriptException;
import java.io.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 字幕/视频 https://www.japonx.net
 *
 * @author Surging
 * @create 2018/9/14
 * @since 1.0.0
 */
public class Japonx {

    //域名
    public static String BASE_URL = "https://www.japonx.net";

    //中文地址
    public static String SUB_URL = "/portal/index/search/zimu_id/35.html";

    public static void main(String[] args) throws IOException {

        // 文件地址
        String path = "D:\\123.json";

        //获取请求连接
        Connection init = Jsoup.connect(BASE_URL + SUB_URL);

        //请求头设置，特别是cookie设置
        init.header("Accept", "text/html, application/xhtml+xml, */*");
        init.header("Content-Type", "application/x-www-form-urlencoded");
        init.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
        //con.header("Cookie", cookie);

        //解析请求结果
        Document initDoc = init.get();
        // 获取总页数
        Elements pageElements = initDoc.getElementsByClass("page-link");
        String page = pageElements.get(pageElements.size() - 2).text();
        //String page = "1";
        Set<Video> videoList = new HashSet<>();
        for (int i = 1; i <= Integer.valueOf(page); i++) {
            // 下一页
            Connection con = Jsoup.connect(BASE_URL + SUB_URL + "&page=" + i);
            Document doc = con.get();
            // 获取详细信息
            Element element = doc.getElementById("works");
            Elements li = element.getElementsByTag("li");
            li.stream().forEach(e -> {
                // 封面图
                String indexUrl = e.getElementsByTag("img").attr("src");
                // 详细页地址
                String detailLink = BASE_URL + e.getElementsByTag("a").first().attr("href");
                try {
                    Document detailElement = Jsoup.connect(detailLink).get();
                    // 影片名称
                    String videoName = detailElement.title();
                    // 简介
                    String description = detailElement.select("meta[name=description]").get(0).attr("content");
                    // 番号
                    String designation = detailElement.getElementsByClass("no-hover").get(0).text();
                    // 时长
                    String runtime = detailElement.getElementsByClass("no-hover").get(1).text();
                    // 演员
                    String directed = detailElement.getElementsByTag("dd").get(2).text();
                    // 日期
                    String createTime = detailElement.getElementsByTag("dd").get(3).text();
                    // 片商
                    String studio = detailElement.getElementsByTag("dd").get(5).text();
                    // 获取加密的eval
                    String fullHtml = detailElement.toString();
                    // 解密eval
                    String evalJs = JsUtils.encode(RegexUtils.evalUrl(fullHtml));
                    // 字幕
                    String subUrl = RegexUtils.subUrl(evalJs);
                    // 视频地址
                    String videoUrl = RegexUtils.videoUrl(evalJs);
                    // 预览图
                    String itemUrl = detailElement.select(".bx-viewport ul li img").eq(1).attr("src");
                    Video video = new Video();
                    video.setIndexUrl(indexUrl);
                    video.setRuntime(runtime);
                    video.setDescription(description);
                    video.setName(videoName);
                    video.setCreateTime(LocalDate.parse(createTime));
                    video.setDirected(directed);
                    video.setVideoUrl(videoUrl);
                    video.setDesignation(designation);
                    video.setStudio(studio);
                    video.setItemUrl(itemUrl);
                    // 下载封面图
                    DownLoadUtils.downLoadFromUrl(video.getIndexUrl(), video.getDesignation(), video.getDirected());
                    // 下载详细图
                    DownLoadUtils.downLoadFromUrl(video.getItemUrl(), video.getDesignation()+"-item", video.getDirected());
                    if (StringUtils.isNotBlank(subUrl)){
                        video.setSubtitleUrl(BASE_URL + subUrl);
                        // 下载字幕
                        DownLoadUtils.downLoadFromUrl(video.getSubtitleUrl(), video.getDesignation(), video.getDirected());
                    }
                    // 保存
                    JsonUtils.save(video);
                    videoList.add(video);
                    System.out.println(video.getDesignation() + "------------------成功");
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    e1.printStackTrace();
                } catch (ScriptException e1) {
                    e1.printStackTrace();
                }
            });
        }
        String json = JSON.toJSONString(videoList);
        try (Writer os = new OutputStreamWriter(new FileOutputStream(path))){
            os.write(json);
        }
        System.out.println("成功抓取:" +videoList.size());
        /*videoList.stream().forEach(e -> {
            try {
                DownLoadUtils.downLoadFromUrl(e.getSubtitleUrl(),e.getDesignation()+".vtt","D:\\Download","");
                System.out.println("成功下载"+e.getName());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/
    }
}
