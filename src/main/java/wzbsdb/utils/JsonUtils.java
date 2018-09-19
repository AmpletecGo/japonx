package wzbsdb.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import wzbsdb.dataobject.Video;

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
        File file = new File(savePath +File.separator+ designation +File.separator+ designation+".json");
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
        videoSet.add(video);
        // 转成JSON
        String json = JSON.toJSONString(videoSet);
        // 写入文件 FileOutputStream(File file, true) true 为不是覆盖
        try (Writer os = new OutputStreamWriter(new FileOutputStream(file))){
            os.write(json);
        }
    }

    /**
     * 进度保存
     * 获取上次查询的第一个番号 已这个命名总的JSON文件
     *
     * @param designation
     * @return
     */
    public static boolean speed(String designation){
        File file = new File(savePath +File.separator+ designation+".json");
        return file.exists();
    }

    /**
     * 保存总JSON
     * @param videoSet
     * @param startName
     */
    public static void saveSpeed(Set<Video> videoSet, String startName, String firstName) throws IOException {
        Set<Video> videoSet1 = new HashSet<>();
        videoSet1.addAll(videoSet);
        if (StringUtils.isNotBlank(startName)){
            File oldFile = new File(savePath +File.separator+ startName+".json");
            String content = FileUtils.readFileToString(oldFile,"UTF-8");
            List<Video> videoList = JSONArray.parseArray(content, Video.class);
            videoSet1.addAll(videoList);
            oldFile.delete();
        }
        File file = new File(savePath +File.separator+ firstName+".json");
        File fileParent = file.getParentFile();
        if(!fileParent.exists()){
            fileParent.mkdirs();
        }
        file.createNewFile();
        // 转成JSON
        String json = JSON.toJSONString(videoSet1);
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
