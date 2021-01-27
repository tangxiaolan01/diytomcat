package test;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private int n;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }
    private Semaphore s1 = new Semaphore(1);
    private Semaphore s2 = new Semaphore(0);
    private Semaphore s3 = new Semaphore(0);
    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0;  i <n;i++){
            s1.acquire();
            printNumber.accept(0);
            if((i&1) == 0){
                s2.release();
            }else {
                s3.release();
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i<=n;i=i+2){
            s3.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i<=n;i=i+2){
            s2.acquire();
            printNumber.accept(i);
            s1.release();
        }
    }


    public static void main(String[] args){
        ZeroEvenOdd evenOdd = new ZeroEvenOdd(2);
        new Thread(()->{
            try {
                evenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                evenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(()->{
            try {
                evenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
