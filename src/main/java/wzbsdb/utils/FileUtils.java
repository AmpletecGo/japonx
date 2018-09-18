package wzbsdb.utils;

import com.alibaba.fastjson.JSON;
import wzbsdb.Video;

import java.io.*;

/**
 * 〈一句话功能简述〉<br>
 *  文件工具类
 *
 * @author Surging
 * @create 2018/9/18
 * @since 1.0.0
 */
public class FileUtils {

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
        // 转成JSON
        String json = JSON.toJSONString(video);
        // 写入文件 true 为不是覆盖
        try (Writer os = new OutputStreamWriter(new FileOutputStream(file,true))){
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
