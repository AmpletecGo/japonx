package wzbsdb.utils;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.StringReader;

public class JsUtils {
    public static final String routeScript = "function getJson(url){var code=url.replace(/^eval/,\"\");return eval(code)};";

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
