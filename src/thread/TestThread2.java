package thread;

public class TestThread2 implements Runnable {
    private int nums =10;
    @Override
    public void run() {
        while (true){
            if(nums<=0){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"拿到了第"+nums--+"张票");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TestThread2(),"小明").start();
        new Thread(new TestThread2(),"小红").start();
        new Thread(new TestThread2(),"小放").start();
    }
}
