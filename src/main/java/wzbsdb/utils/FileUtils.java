package wzbsdb.utils;

import com.alibaba.fastjson.JSON;
import wzbsdb.Video;

import java.awt.datatransfer.SystemFlavorMap;
import java.io.*;

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
        String json = JSON.toJSONString(video);
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
