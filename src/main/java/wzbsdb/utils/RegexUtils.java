package wzbsdb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 〈一句话功能简述〉<br>
 *  正则表达式工具类
 *
 * @author Surging
 * @create 2018/9/18
 * @since 1.0.0
 */
public class RegexUtils {

    /**
     * 获取视频地址
     * @param scriptResult
     * @return
     */
    public static String videoUrl(String scriptResult) {
        String regex = "http[s]?://[^\\s]+.mp4";
        return stringInit(regex, scriptResult);
    }

    /**
     * 获取字幕地址
     * @param scriptResult
     * @return
     */
    public static String subUrl(String scriptResult) {
        String regex = "/upload/admin/.+\\.vtt";
        return stringInit(regex, scriptResult);
    }

    /**
     * 获取预览图地址
     * @param scriptResult
     * @return
     */
    /*public static String itemUrl(String scriptResult) {
        String regex = "https://image.japronx.com/[^\\s]+.png";
        return stringInit(regex, scriptResult);
    }*/

    /**
     * 获取加密的字符串
     * @param scriptResult
     * @return
     */
    public static String evalUrl(String scriptResult) {
        String reg = "eval.*";
        return stringInit(reg, scriptResult);
    }

    /**
     * 返回处理过的字符
     * 没有获取到则返回 null
     *
     * @param regex        正则规则
     * @param scriptResult 需要处理的字符串
     * @return
     */
    public static String stringInit(String regex, String scriptResult) {
        List<String> stringList = new ArrayList<>();
        Pattern p = Pattern.compile(regex);
        //让正则对象和要作用的字符串相关联。获取匹配器对象。
        Matcher m = p.matcher(scriptResult);
        while (m.find()) {
            stringList.add(m.group());
        }
        if (stringList.size() == 0) {
            return null;
        }
        return stringList.get(0);
    }
}
