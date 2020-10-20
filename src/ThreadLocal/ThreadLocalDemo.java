package ThreadLocal;

public class ThreadLocalDemo {
    //银行
    static class Bank{
        private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue() {
                return 0;
            }
        };
        //取钱
        private int getMoney(){
            return threadLocal.get();
        }
        //存钱
        private void setMoney(int money){
            threadLocal.set(threadLocal.get()+money);
        }
    }
    //转帐
    static class Trancfer implements Runnable{
        private Bank bank;
        public Trancfer(Bank bank){
            this.bank =bank;
        }
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                bank.setMoney(10);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"转了"+bank.getMoney());
            }
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        Thread thread1 = new Thread(new Trancfer(bank),"线程一");
        Thread thread2 = new Thread(new Trancfer(bank),"线程二");
        thread1.start();
        thread2.start();
    }
}
