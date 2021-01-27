package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

class FizzBuzz {
    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }
    CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 1;i<=n; i++){
            if(i %3 == 0 && i % 5 != 0){
               printFizz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 1;i<=n; i++){
            if(i %5 == 0 && i % 3 != 0){
                printBuzz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 1;i<=n; i++){
            if(i %5 == 0 && i % 3 == 0){
                printFizzBuzz.run();
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i<=n; i++){
            if(i %5 != 0 && i % 3 != 0){
               printNumber.accept(i);
            }
            try {
                cyclicBarrier.await();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[]){
        Runnable printFizz = ()-> System.out.println("fizz");
        Runnable rBuzz = () -> System.out.println("buzz");
        Runnable rFizzBuzz = () -> System.out.println("fizzbuzz");
        IntConsumer pNumber = x -> System.out.println(x);

        FizzBuzz fizzBuzz = new FizzBuzz(16);
        Runnable f = () ->{
            try {
                fizzBuzz.fizz(printFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable b = () ->{
            try {
                fizzBuzz.buzz(rBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable c = () ->{
            try {
                fizzBuzz.fizzbuzz(rFizzBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable d = () ->{
            try {
                fizzBuzz.number(pNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t1 = new Thread(f);
        Thread t2 = new Thread(b);

        Thread t3 = new Thread(c);

        Thread t4 = new Thread(d);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}