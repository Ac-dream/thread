package thread;

public class TestRace implements Runnable{
    private static String winner;
    @Override
    public void run() {
        for(int i =0;i<=100;i++){
            boolean flag = isWinner(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"跑了"+i);
        }

    }
    public boolean isWinner(int i){
        if(winner!=null) {
            return true;
        }
        else {
            if(i>=100){
                winner=Thread.currentThread().getName();
                System.out.println("获胜"+winner);
                return true;
            }else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TestRace(),"兔子").start();
        new Thread(new TestRace(),"乌龟").start();
    }
}
