package test;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CycleBarrierTest {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args){
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("线程A" + Thread.currentThread().getName() +"执行第一步");
                    cyclicBarrier.await();
                    System.out.println("线程A" + Thread.currentThread().getName() +"执行第二步");
                    cyclicBarrier.await();
                    System.out.println("线程A" + Thread.currentThread().getName() +"执行第三步");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        service.submit(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("线程B" + Thread.currentThread().getName() +"执行第一步");
                    cyclicBarrier.await();
                    System.out.println("线程B" + Thread.currentThread().getName() +"执行第二步");
                    cyclicBarrier.await();
                    System.out.println("线程B" + Thread.currentThread().getName() +"执行第三步");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        service.shutdown();
    }
}
