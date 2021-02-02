package cn.how2j.diytomcat.http;

import cn.how2j.diytomcat.Bootstrap;
import cn.how2j.diytomcat.catalina.Context;
import cn.how2j.diytomcat.catalina.Host;
import cn.how2j.diytomcat.util.MiniBrowser;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Request {
    private String requestString;
    private String uri;
    private Socket socket;
    private Context context;
    private Host host;
    private void parseHttpRequest() throws IOException {
       InputStream inputStream =  socket.getInputStream();
        byte[] bytes = MiniBrowser.readBytes(inputStream);

        requestString = new String(bytes,"utf-8");
    }

    public Request(Socket socket,Host host) throws IOException {
        this.socket = socket;
        this.host = host;

        parseHttpRequest();
        if(StrUtil.isEmpty(requestString))
            return;
        parseUri();
        parseContext();
        if(!"/".equals(context.getPath())){
            uri = StrUtil.removePrefix(uri,context.getPath());
        }
    }

    private void parseUri() {
        String temp = StrUtil.subBetween(requestString," "," ");

        if(!temp.contains("?")){
            uri = temp;
            return;
        }

        temp = StrUtil.subBefore(temp,"?",false);
        uri = temp;
    }

    private void parseContext(){
        String path = StrUtil.subBetween(uri,"/","/");
        if(null == path){
            path = "/";
        }else {
            path = "/" + path;
        }

        context = host.getContext(path);
        if(context == null){
            context = host.getContext("/");
        }
    }
    public String getRequestString() {
        return requestString;
    }

    public String getUri() {
        return uri;
    }

    public Context getContext() {
        return context;
    }
}
