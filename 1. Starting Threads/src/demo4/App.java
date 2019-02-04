package demo4;

public class App {
    public static void main(String[] args){
        Runnable task1 = ()->{
            for ( int i=0; i<10; i++){
                System.out.println("Hello "+ i);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread1 = new Thread(task1);
        thread1.start();
    }
}
