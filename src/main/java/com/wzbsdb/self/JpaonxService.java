package com.wzbsdb.self;

import com.wzbsdb.common.Constant;
import com.wzbsdb.dataobject.Task;
import com.wzbsdb.dataobject.Video;
import com.wzbsdb.service.ITaskService;
import com.wzbsdb.service.IVideoService;
import com.wzbsdb.utils.HttpClientUtil;
import com.wzbsdb.utils.JsUtils;
import com.wzbsdb.utils.RegexUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.script.ScriptException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 *
 * @author Surging
 * @create 2018/10/15
 * @since 1.0.0
 */
@Slf4j
@Component
public class JpaonxService {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private ITaskService taskService;

    public void getJpaonx() {
        //获取请求连接
        Connection init = Jsoup.connect(Constant.BASE_URL + Constant.SUB_URL);
        //请求头设置，特别是cookie设置
        init.header("Accept", "text/html, application/xhtml+xml, */*");
        init.header("Content-Type", "application/x-www-form-urlencoded");
        init.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.92 Safari/537.36");
        //con.header("Cookie", cookie);

        //解析请求结果
        Document initDoc = null;
        try {
            initDoc = init.get();
            // 获取总页数
            Elements pageElements = initDoc.getElementsByClass("page-link");
            String page = pageElements.get(pageElements.size() - 2).text();
            // 保存总进度
            Task task = new Task();
            Integer count = 0;
            // 开始爬虫 并记录开始时间
            task.setStartTime(LocalDateTime.now());
            start:
            for (int i = 1; i <= Integer.valueOf(1); i++) {
                // 跳转下一页
                Document doc = Jsoup.connect(Constant.BASE_URL + Constant.SUB_URL + "&page=" + i).get();
                // 获取详细信息
                Element element = doc.getElementById("works");
                // 获取一页所有的数据
                Elements li = element.getElementsByTag("li");
                // 遍历
                for (Element e : li) {
                    // 封面图
                    String indexUrl = e.getElementsByTag("img").attr("src");
                    // 详细页地址
                    String detailLink = Constant.BASE_URL + e.getElementsByTag("a").first().attr("href");
                    // 详细页页面
                    Document detailElement = Jsoup.connect(detailLink).get();
                    // 番号
                    String designation = detailElement.getElementsByClass("no-hover").get(0).text();
                    // 数量++
                    count++;
                    if (count == 1) {
                        task.setStart(designation);
                    }
                    task.setStop(designation);
                    // 查询爬虫记录 是否上次记录  true 则 暂停爬虫
                    if (taskService.isExist(designation)){
                        // 暂停爬虫
                        break start;
                    }

                    /*if (JsonUtils.speed(designation)) {
                        startName = designation;
                        break start;
                    }*/
                    // 影片名称
                    String videoName = detailElement.title();
                    // 简介
                    String description = detailElement.select("meta[name=description]").get(0).attr("content");
                    // 时长
                    String runtime = detailElement.getElementsByClass("no-hover").get(1).text();
                    // 演员
                    String directed = detailElement.getElementsByTag("dd").get(2).text();
                    // 日期
                    String createTime = detailElement.getElementsByTag("dd").get(3).text();
                    // 片商
                    String studio = detailElement.getElementsByTag("dd").get(5).text();
                    // 预览图
                    String itemUrl = detailElement.select(".bx-viewport ul li img").eq(1).attr("src");

                    /**
                     * 改版了这边废弃 以前加密的JS是直接写在页面上  用正则取出来的
                     * 获取加密的eval字段
                     */
                    // String fullHtml = detailElement.toString();
                    // String js = RegexUtils.evalUrl(fullHtml);

                    //获取id地址
                    String id = detailLink.substring(detailLink.lastIndexOf("/") + 1).replace(".html", "");
                    // 访问JS地址   获取加密的eval
                    String js = HttpClientUtil.sendGet(Constant.BASE_URL + Constant.JS_URL + id);
                    // 解密eval
                    String evalJs = JsUtils.encode(RegexUtils.evalUrl(js));
                    // 字幕 正则
                    String subUrl = RegexUtils.subUrl(evalJs);
                    // 视频地址 正则
                    String videoUrl = RegexUtils.videoUrl(evalJs);

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
                    // DownLoadUtils.downLoadFromUrl(video.getIndexUrl(), video.getDesignation(), video.getDirected());
                    // 下载详细图
                    // DownLoadUtils.downLoadFromUrl(video.getItemUrl(), video.getDesignation() + "-item", video.getDirected());
                    // 字幕不为空时 需要下载字幕
                    if (StringUtils.isNotBlank(subUrl)) {
                        video.setSubtitleUrl(Constant.BASE_URL + subUrl);
                        // 下载字幕
                        // DownLoadUtils.downLoadFromUrl(video.getSubtitleUrl(), video.getDesignation(), video.getDirected());
                    }
                    videoService.save(video);
                }
            }
            task.setStopTime(LocalDateTime.now());
            task.setCount(count);
            taskService.save(task);
            System.out.println("成功抓取:" + count + "条数据\n开始的番号" + task.getStart());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}
