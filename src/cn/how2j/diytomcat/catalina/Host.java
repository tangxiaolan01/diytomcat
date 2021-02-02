package cn.how2j.diytomcat.catalina;

import cn.how2j.diytomcat.util.Constant;
import cn.how2j.diytomcat.util.ServerXMLUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Host {
    private String name;
    private Map<String,Context> contextMap;

    public Host(){
        this.contextMap = new HashMap<>();
        this.name = ServerXMLUtil.getHostName();
        scanContextInServerXMl();
        scanContextOnWebAppsFolder();

    }

    public String getName() {
        return name;
    }

    public Map<String, Context> getContextMap() {
        return contextMap;
    }

    private void loadContext(File folder){
        String path = folder.getName();
        if("ROOT".equals(path)){
            path = "/";
        }else {
            path = "/"+ path;
        }
        String docBase = folder.getAbsolutePath();
        Context context = new Context(path,docBase);
        contextMap.put(path,context);
    }

    private void scanContextOnWebAppsFolder(){
        File[] files = Constant.webappsFolder.listFiles();
        for(File  file: files){
            if(!file.isDirectory())
                continue;
            loadContext(file);
        }
    }

    private void scanContextInServerXMl(){
        List<Context> contexts = ServerXMLUtil.getContexts();
        for(Context context : contexts){
            contextMap.put(context.getPath(),context);
        }
    }

    public Context getContext(String path){
        return this.contextMap.get(path);
    }
}
