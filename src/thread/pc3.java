package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class pc3 {
    public static void main(String[] args) {
        
        Num3 num3 = new Num3();
        new Thread(()->{
            for (int i=0;i<10;i++){
                num3.a();
            }
        },"a").start();
        new Thread(()->{
            for (int i=0;i<10;i++){
                num3.b();
            }
        },"b").start();
        new Thread(()->{
            for (int i=0;i<10;i++){
                num3.c();
            }
        },"c").start();
    }
}
class Num3{
    private int num =1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    public void  a(){
        try {
            lock.lock();
            while (num!=1){
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void  b(){
        try {
            lock.lock();
            while (num!=2){
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void  c(){
        try {
            lock.lock();
            while (num!=3){
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+":"+num);
            num=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
