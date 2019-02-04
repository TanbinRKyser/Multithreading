package demo;

public class App {
    public static void main(String[] args) throws InterruptedException {
        final Runner RUNNER = new Runner();

        Thread t1 = new Thread( () -> {
            try {
                RUNNER.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread( () -> {
            try {
                RUNNER.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        RUNNER.finished();
    }
}
