package cn.how2j.diytomcat.util;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtil {
    private static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(20,20,60
            , TimeUnit.SECONDS,new LinkedBlockingDeque<>(10));
    public static void run(Runnable r){
        poolExecutor.execute(r);
    }
}
