package thread;

public class Thread2 implements Runnable {
    @Override
    public void run() {
        for (int i=0;i<200;i++){
            System.out.println("eat");
        }
    }
    public static void main(String[] args) {
        for (int i=0;i<200;i++){
            System.out.println("read");
        }
        new Thread(new Thread2()).start();
    }
}
