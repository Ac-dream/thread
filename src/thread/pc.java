package thread;

public class pc {
    public static void main(String[] args) {
        Num num = new Num();
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
class Num{
    private int num =0;

    public synchronized void increment() throws InterruptedException {
        while (num!=0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+":"+num);
        this.notifyAll();
    }
    public synchronized void decrement() throws InterruptedException {
        while (num==0){
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+":"+num);
        this.notifyAll();
    }
}
