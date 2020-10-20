package thread;

public class Thread1 extends Thread{
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
        new Thread1().start();
    }
}
