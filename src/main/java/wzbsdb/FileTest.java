package wzbsdb;

import com.alibaba.fastjson.JSON;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws IOException {
        String path = "D:\\123.json";
        Video video = new Video();
        video.setName("一种");
        video.setDescription("不知道");
        video.setSubtitleUrl("https://blog.csdn.net/u011118873/article/details/51553117");
        String json = JSON.toJSONString(video);
        try (Writer os = new OutputStreamWriter(new FileOutputStream(path))){
            os.write(json);
        }
    }
}
