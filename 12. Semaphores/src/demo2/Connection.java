package demo2;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection instance = new Connection();
    private int count;
    private Semaphore semaphores = new Semaphore(10, true);

    private Connection() {
        super();
    }


    public static Connection getInstance(){
        return instance;
    }

    public void connect(){
        try {
            semaphores.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            doConnect();
        } finally {
            semaphores.release();
        }
    }

    public void doConnect(){
        synchronized (this){
            count++;
            System.out.println("No of connections: " + count);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            count--;
        }
    }
}
