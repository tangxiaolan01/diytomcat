package cn.how2j.diytomcat.catalina;

import cn.how2j.diytomcat.http.Request;
import cn.how2j.diytomcat.http.Response;
import cn.how2j.diytomcat.util.Constant;
import cn.how2j.diytomcat.util.ServerXMLUtil;
import cn.how2j.diytomcat.util.ThreadPoolUtil;
import cn.how2j.diytomcat.util.WebXMLUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.LogFactory;
import cn.hutool.system.SystemUtil;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Server {
    private Service service;

    public Server(){
        this.service = new Service(this);
    }

    public void start(){
        logJVM();
        init();
    }

    public void init(){
        try {

            int port = 18080;
            ServerSocket ss = new ServerSocket(port);
            while(true) {
                Socket s =  ss.accept();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Request request = null;
                        try {
                            request = new Request(s,service);
                            Response response = new Response();

                            String uri = request.getUri();
                            if(null==uri)
                                return;
                            System.out.println("uri=====>" +  uri);
                            Context context = request.getContext();

                            if(uri.endsWith("500.html")){
                                throw  new Exception("this is a deliberately created exception");
                            }

                            if("/".equals(uri)){
                                uri = WebXMLUtil.getWelcomeFile(request.getContext());
                            }

                                String fileName = StrUtil.removePrefix(uri, "/");
                                System.out.println("docBace =====>"+ context.getDocBase());
                                System.out.println("fileName =====>"+ fileName);

                                File file = FileUtil.file(context.getDocBase(),fileName);
                                if(file.exists()){
                                    String extName = FileUtil.extName(fileName);
                                    String extType = WebXMLUtil.getMimeType(extName);
                                    response.setContentType(extType);
                                   /* String fileContent = FileUtil.readUtf8String(file);
                                    response.getWriter().println(fileContent);*/

                                   byte[] body = FileUtil.readBytes(file);
                                   response.setBody(body);

                                    if(fileName.endsWith("timeConsume.html")){
                                        ThreadUtil.sleep(1000);
                                    }


                                }
                                else{
                                    handle404(s,uri);
                                    return;
                                }

                            handle200(s, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                            handle500(s,e);
                        }finally {
                            try{
                                if(!s.isClosed()){
                                    s.close();
                                }
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                    }
                };
                ThreadPoolUtil.run(runnable);
            }
        } catch (IOException e) {
            LogFactory.get().error(e);
            e.printStackTrace();
        }
    }

    private static void logJVM() {
        Map<String,String> infos = new LinkedHashMap<>();
        infos.put("Server version", "How2J DiyTomcat/1.0.1");
        infos.put("Server built", "2020-04-08 10:20:22");
        infos.put("Server number", "1.0.1");
        infos.put("OS Name\t", SystemUtil.get("os.name"));
        infos.put("OS Version", SystemUtil.get("os.version"));
        infos.put("Architecture", SystemUtil.get("os.arch"));
        infos.put("Java Home", SystemUtil.get("java.home"));
        infos.put("JVM Version", SystemUtil.get("java.runtime.version"));
        infos.put("JVM Vendor", SystemUtil.get("java.vm.specification.vendor"));

        Set<String> keys = infos.keySet();
        for (String key : keys) {
            LogFactory.get().info(key+":\t\t" + infos.get(key));
        }
    }


    private static void handle200(Socket s, Response response) throws IOException {
        String contentType = response.getContentType();
        String headText = Constant.response_head_202;
        headText = StrUtil.format(headText, contentType);
        byte[] head = headText.getBytes();

        byte[] body = response.getBody();

        byte[] responseBytes = new byte[head.length + body.length];
        ArrayUtil.copy(head, 0, responseBytes, 0, head.length);
        ArrayUtil.copy(body, 0, responseBytes, head.length, body.length);

        OutputStream os = s.getOutputStream();
        os.write(responseBytes);
    }

    protected void handle404(Socket s, String uri) throws IOException {
        OutputStream os = s.getOutputStream();
        String responseText = StrUtil.format(Constant.textFormat_404,uri,uri);
        responseText =   Constant.response_head_404 + responseText;
        byte[] responseByte = responseText.getBytes("utf-8");
        os.write(responseByte);
    }

    protected void handle500(Socket s,Exception e){
        try{
            OutputStream os = s.getOutputStream();
            StackTraceElement traceElement[] = e.getStackTrace();
            StringBuffer sb = new StringBuffer();
            sb.append("\r\n");
            for(StackTraceElement st : traceElement){
                sb.append("\t");
                sb.append(st.toString());
                sb.append("\r\n");
            }

            String msg = e.getMessage();
            if(null != msg && msg.length()>20){
                msg.substring(0,19);
            }
            String text = StrUtil.format(Constant.textFormat_500,msg,e.toString(),sb.toString());
            text = Constant.response_head_500 + text;
            byte[] responseByte = text.getBytes("utf-8");
            os.write(responseByte);

        }catch (IOException e1){
            e1.printStackTrace();
        }
    }
}
