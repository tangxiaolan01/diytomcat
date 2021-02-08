package cn.how2j.diytomcat.util;

import cn.how2j.diytomcat.catalina.Context;
import cn.how2j.diytomcat.catalina.Engine;
import cn.how2j.diytomcat.catalina.Host;
import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerXMLUtil {
    public static List<Context> getContexts(){
        List<Context> result = new ArrayList<>();
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document d = Jsoup.parse(xml);
        Elements es = d.select("Context");
        for(Element e: es){
            String path = e.attr("path");
            String docBase = e.attr("docBase");
            Context context = new Context(path,docBase);
            result.add(context);
        }
        return result;
    }
    public static String getHostName(){
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document d = Jsoup.parse(xml);
        Element host = d.select("Host").first();
        return host.attr("name");

    }

    public static String getEngineDefaultHost(){
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document document = Jsoup.parse(xml);
        Element engine = document.select("Engine").first();
        return engine.attr("defautHost");
    }

    public static List<Host> getHosts(Engine engine){
        List<Host> result = new ArrayList<>();
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document document = Jsoup.parse(xml);
        Elements es = document.select("Host");
        for(Element e: es){
            String name = e.attr("name");
            Host host = new Host(name,engine);
            result.add(host);
        }
        return result;
    }

    public static String getServiceName() {
        String xml = FileUtil.readUtf8String(Constant.serverXmlFile);
        Document document = Jsoup.parse(xml);
        Element engine = document.select("Service").first();
        return engine.attr("name");
    }
}
