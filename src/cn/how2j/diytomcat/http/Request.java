package cn.how2j.diytomcat.http;

import cn.how2j.diytomcat.util.MiniBrowser;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Request {
    private String requestString;
    private String uri;
    private Socket socket;

    private void parseHttpRequest() throws IOException {
       InputStream inputStream =  socket.getInputStream();
        byte[] bytes = MiniBrowser.readBytes(inputStream);

        requestString = new String(bytes,"utf-8");
    }

    public Request(Socket socket) throws IOException {
        this.socket = socket;
        parseHttpRequest();
        if(StrUtil.isEmpty(requestString))
            return;
        parseUri();
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

    public String getRequestString() {
        return requestString;
    }

    public String getUri() {
        return uri;
    }
}
