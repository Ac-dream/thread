package atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo1 {
    //private static int n;
    private static AtomicInteger atomicInteger;
    public static void main(String[] args) {
        int j=0;
        while (j<100){
            //n=0;
            atomicInteger = new AtomicInteger(0);
            new Thread(()->{
                for(int i=0;i<1000;i++){
                    //n++;
                    atomicInteger.getAndIncrement();
                }
            }).start();
            new Thread(()->{
                for(int i=0;i<1000;i++){
                    //n++;
                    atomicInteger.getAndIncrement();
                }
            }).start();
            System.out.println("n最终为"+atomicInteger.get());
            j++;
        }
    }
}
