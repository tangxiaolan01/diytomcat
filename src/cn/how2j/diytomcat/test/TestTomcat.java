package cn.how2j.diytomcat.test;

import cn.how2j.diytomcat.util.MiniBrowser;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.NetUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestTomcat {
    private static int port = 18080;
    private static String ip = "127.0.0.1";
    @BeforeClass
    public static void beforeClass() {
        //所有测试开始前看diy tomcat 是否已经启动了
        if(NetUtil.isUsableLocalPort(port)) {
            System.err.println("请先启动 位于端口: " +port+ " 的diy tomcat，否则无法进行单元测试");
            System.exit(1);
        }
        else {
            System.out.println("检测到 diy tomcat已经启动，开始进行单元测试");
        }
    }

    @Test
    public void testHelloTomcat() {
        String html = getContentString("/");
        Assert.assertEquals(html,"Hello DIY Tomcat from how2j.cn");
    }

    private String getContentString(String uri) {
        String url = StrUtil.format("http://{}:{}{}", ip,port,uri);
        String content = MiniBrowser.getContentString(url);
        return content;
    }

    @Test
    public void testTimeConsumeHtml() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(20,20,
                60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(10));
        TimeInterval timeInterval = DateUtil.timer();
        for(int i = 0;i < 3;i++){
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    getContentString("/timeConsume.html");
                }
            });
        }
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1,TimeUnit.HOURS);

        long duration = timeInterval.intervalMs();
        System.out.println(duration);
        Assert.assertTrue(duration < 3000);
    }

    @Test
    public void testaIndex() {
        String html = getContentString("/a");
        Assert.assertEquals(html,"Hello DIY Tomcat from index.html@a");
    }
}
