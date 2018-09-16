package wzbsdb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtils {

    public static String videoUrl(String scriptResult) {
        //String reg = "[a-zA-z]+://.+\\.mp4";
        String reg = "https://player.japronx.com/.+\\.mp4";
        return stringInit(reg, scriptResult);
    }

    public static String subUrl(String scriptResult) {
        String reg = "/upload/admin/.+\\.vtt";
        return stringInit(reg, scriptResult.replace("url':'", ""));
    }

    public static String evalUrl(String scriptResult) {
        String reg = "eval.*";
        return stringInit(reg, scriptResult);
    }

    /**
     * @param reg          规则
     * @param scriptResult 处理的字符串
     * @return
     */
    public static String stringInit(String reg, String scriptResult) {
        List<String> stringList = new ArrayList<>();
        Pattern p = Pattern.compile(reg);
        //让正则对象和要作用的字符串相关联。获取匹配器对象。
        Matcher m = p.matcher(scriptResult);
        while (m.find()) {
            stringList.add(m.group());
        }
        if (stringList.size() == 0) {
            return "";
        }
        return stringList.get(0);
    }
}
