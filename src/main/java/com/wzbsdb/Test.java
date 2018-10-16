package com.wzbsdb;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        String routeScript = "function getJson(url) {\n" +
                "        var code = url.replace(/^eval/, '');\n" +
                "        return eval(code);\n" +
                "    }";
        //String routeScript = "function getJson(url){var code= code.replace(/^eval/,\"\");return eval(code)};";

        String scriptResult = "";//脚本的执行结果

        //创建一个脚本引擎管理器
        ScriptEngineManager manager = new ScriptEngineManager();
        //获取一个指定的名称的脚本引擎
        ScriptEngine engine = manager.getEngineByName("js");
        engine.eval(new StringReader(routeScript));
        //3.将引擎转换为Invocable，这样才可以掉用js的方法
        Invocable invocable = (Invocable) engine;
        //4.使用 invocable.invokeFunction掉用js脚本里的方法，第一個参数为方法名，后面的参数为被调用的js方法的入参
        scriptResult = (String) invocable.invokeFunction("getJson", "eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\\\b'+e(c)+'\\\\b','g'),k[c])}}return p}('l k=j h({i:m.n(\\'6\\'),r:2,q:\\'#p\\',o:\\'g-s\\',f:0.3,c:2,e:d,a:\\'b\\',6:{4:\\'9://E.8.7/J/1.t\\',H:\\'9://G.8.7/1.L\\'},M:{\\'4\\':\\'/N/K/F/x.w\\',v:\\'u\\',y:\\'z\\',D:\\'5%\\',C:\\'#B\\'},A:[{I:\\'歡迎使用狩都高速\\',}]});',50,50,'|ssni229_hd|false||url||video|com|japronx|https|preload|auto|screenshot|true|hotkey|volume|zh|DPlayer|element|new|dp|var|document|getElementById|lang|FADFA3|theme|autoplay|tw|mp4|webvtt|type|vtt|a02510ef5809cc84515ca8e763110dfe|fontSize|24px|contextmenu|ffffff|color|bottom|player|20180702|image|thumbnails|text|Stream|admin|png|subtitle|upload'.split('|'),0,{}))");
        String reg = "[a-zA-z]+://.+\\.mp4";
        Pattern p = Pattern.compile(reg);
        //让正则对象和要作用的字符串相关联。获取匹配器对象。
        Matcher m = p.matcher(scriptResult);
        while (m.find()) {
            System.out.println(m.group());
        }

        // 字幕
        String subReg = "url':'/.+\\.vtt";
        Pattern subP = Pattern.compile(subReg);
        Matcher s = subP.matcher(scriptResult);
        while (s.find()) {
            System.out.println(s.group().replace("url':'", ""));
        }
    }
}
