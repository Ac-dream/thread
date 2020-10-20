package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class pc1 {
    public static void main(String[] args) {
        Num1 num = new Num1();
        new Thread(()->{
            for (int i=0;i<100;i++){
                try {
                    num.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i=0;i<100;i++){
                try {
                    num.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
class Num1{
    private int num =0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public  void increment() throws InterruptedException {
        try {
            lock.lock();
            while (num!=0){
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName()+":"+num);
            condition.signalAll();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }

    }
    public  void decrement() throws InterruptedException {
        try {
            lock.lock();
            while (num==0){
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName()+":"+num);
            condition.signalAll();
        }catch (Exception e){
            System.out.println(e);
        }finally {
            lock.unlock();
        }

    }
}
