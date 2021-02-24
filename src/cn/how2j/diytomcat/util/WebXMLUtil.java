package cn.how2j.diytomcat.util;

import cn.how2j.diytomcat.catalina.Context;
import cn.hutool.core.io.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class WebXMLUtil {
    private static Map<String,String> mineTypeMapping = new HashMap<>();
    public static String getWelcomeFile(Context context){
        String xml = FileUtil.readUtf8String(Constant.webXmlFile);
        Document d = Jsoup.parse(xml);
        Elements es = d.select("welcome-file");
        for(Element e: es){
            String welcomeFileName = e.text();
            File f = new File(context.getDocBase(),welcomeFileName);
            if(f.exists()){
                return f.getName();
            }
        }
        return "index.html";
    }

    private static void initMimeType(){
        String xml = FileUtil.readUtf8String(Constant.webXmlFile);
        Document d = Jsoup.parse(xml);
        Elements es = d.select("mime-mapping");
        for(Element e : es){
            String extName = e.select("extension").first().text();
            String mimeType = e.select("mime-type").first().text();
            mineTypeMapping.put(extName,mimeType);
        }
    }

    public static synchronized  String getMimeType(String extName){
        if(mineTypeMapping.isEmpty()){
            initMimeType();
        }
        String mimeType = mineTypeMapping.get(extName);
        if(mimeType == null){
            mimeType="text/html";
        }
        return mimeType;
    }


}
