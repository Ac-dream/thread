package cn.safe;

public class Ticket implements Runnable{
    private int num=100;

    @Override
    public void run() {
        while (true){
            if (num>0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String name=Thread.currentThread().getName();
                System.out.println("线程"+name+"销售"+num--);
            }
        }
    }
}
