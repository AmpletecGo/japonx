package wzbsdb.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadUtils {

    private static String savePath ="D:\\Download";

    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName, String directed) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        // 得到番号
        String designation = fileName.substring(0,fileName.indexOf("-"));

        //文件保存位置
        File saveDir = new File(savePath+"\\"+designation+"\\"+directed);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        // 取出所有文件夹
        File[] files = saveDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                if (fileName.equalsIgnoreCase(files[i].getName())){
                    return;
                }
            }
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        //System.out.println("info:"+url+" download success");
    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()){
            byte[] buffer = new byte[1024];
            int len = 0;
            while((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
    }

    public static void main(String[] args) {
        try{
            downLoadFromUrl("https://www.japonx.net/upload/admin/20180910/6b55eba17d9bd7890f48c59443dbdfef.vtt",
                    "MUDR-048.vtt","平手茜");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
