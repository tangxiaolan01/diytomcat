package cn.how2j.diytomcat.http;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

public class Response {
    private StringWriter stringWriter;
    private PrintWriter writer;
    private String contentType;
    private byte[] body;

    public Response() {
        this.stringWriter = new StringWriter();
        this.writer = new PrintWriter(stringWriter);
        this.contentType = "text/html";
    }

    public void setContentType(String contentType){
        this.contentType = contentType;
    }

    public String getContentType(){
        return contentType;
    }

    public PrintWriter getWriter(){
        return writer;
    }

    public void setBody(byte[] body){
        this.body = body;
    }

    public byte[] getBody() throws UnsupportedEncodingException {
        if(body == null){
            String content = stringWriter.toString();
            body = content.getBytes("utf-8");
        }

        return body;
    }


}
