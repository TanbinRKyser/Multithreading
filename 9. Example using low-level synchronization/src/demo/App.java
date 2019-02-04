package demo;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Processor PROCESSOR = new Processor();

        Thread t1 = new Thread( () -> {
            try {
                PROCESSOR.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread( () -> {
            try {
                PROCESSOR.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
