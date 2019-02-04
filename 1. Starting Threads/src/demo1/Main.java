package demo1;

class Runner extends Thread{
    @Override
    public void run() {
        for ( int i=0; i<10; i++){
            System.out.println("Hello "+ i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Main {
    public static void main(String[] args){
        //System.out.println("Hello World!!!");
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }
}
