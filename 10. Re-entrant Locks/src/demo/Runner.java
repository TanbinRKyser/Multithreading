package demo;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    private void increment(){
        for (int i = 0; i<10000; i++){
            count++;
        }
    }

    public void firstThread() throws InterruptedException{
        lock.lock();

        System.out.println("Waiting....");
        condition.await();

        System.out.println("Resumed.");

        try {
            increment();
        } finally {
            lock.unlock();
        }

    }


    public void secondThread() throws InterruptedException{
        // Just to make sure that first thread will start first.
        Thread.sleep(2000);

        lock.lock();

        System.out.println("Press Enter -");
        new Scanner(System.in).nextLine();
        System.out.println("You have pressed the return key.");

        // This will send a notification to the first thread to resume. Just like notifyAll()
        condition.signal();

        try {
            increment();
        } finally {
            lock.unlock();
        }
    }


    public void finished(){
        System.out.println("Count is: " + count);
    }
}
