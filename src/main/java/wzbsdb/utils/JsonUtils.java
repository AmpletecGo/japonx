package wzbsdb.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.LineInputStream;
import org.apache.commons.io.FileUtils;
import wzbsdb.Video;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 *  文件工具类
 *
 * @author Surging
 * @create 2018/9/18
 * @since 1.0.0
 */
public class JsonUtils {

    private static String savePath ="D:\\Download";

    public static void save(Video video) throws IOException {
        // 得到番号
        String fileName = video.getDesignation().toUpperCase();
        String designation = fileName.substring(0,fileName.indexOf("-"));

        //文件保存位置
        File file = new File(savePath+"\\"+designation+"\\"+designation+".json");
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
        // 读取JSON文件
        String content = FileUtils.readFileToString(file,"UTF-8");
        List<Video> videoList = JSONArray.parseArray(content, Video.class);
        Set<Video> videoSet = new HashSet<>();
        if (videoList != null) {
            videoSet.addAll(videoList);
        }
        /*if (videoList != null){
            // 是否已经写入 文件 去重
            Video video1 = videoList.stream().filter(e -> e.getDesignation().equalsIgnoreCase(designation)).findFirst().orElse(null);
            if (video1 != null) {
                return;
            }
        }*/
        videoSet.add(video);
        // 转成JSON
        String json = JSON.toJSONString(videoSet);
        // 写入文件 FileOutputStream(File file, true) true 为不是覆盖
        try (Writer os = new OutputStreamWriter(new FileOutputStream(file))){
            os.write(json);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            Video video = new Video();
            video.setDesignation("MUDR-050");
            video.setName("我真不是132");
            try {
                save(video);
                System.out.println("输出");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
