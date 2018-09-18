package wzbsdb.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.StringReader;

/**
 * 〈一句话功能简述〉<br>
 *  调用原生JS方法 解密一段JS
 *
 * @author Surging
 * @create 2018/9/18
 * @since 1.0.0
 */
public class JsUtils {

    /**
     * 定义自己的 JS方法
     * function getJson(url) {
     *     var code = url.replace(/^eval/, "");
     *     return eval(code)
     * };
     */
    public static final String routeScript = "function getJson(url){var code=url.replace(/^eval/,\"\");return eval(code)};";

    /**
     * 传入需要eval 解密的字符串
     * @param evalCode
     * @return
     * @throws ScriptException
     * @throws NoSuchMethodException
     */
    public static String encode(String evalCode) throws ScriptException, NoSuchMethodException {
        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(new StringReader(routeScript));
        //3.将引擎转换为Invocable，这样才可以掉用js的方法
        Invocable invocable = (Invocable) engine;
        //4.使用 invocable.invokeFunction掉用js脚本里的方法，第一個参数为方法名，后面的参数为被调用的js方法的入参
        return (String) invocable.invokeFunction("getJson", evalCode);
    }
}
