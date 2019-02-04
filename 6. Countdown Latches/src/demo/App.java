//Multithreading 6: Counting Latches

package demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class Processor implements Runnable{

    private CountDownLatch latch;


    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("Started...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Decreasing the no of latch.
        latch.countDown();
    }
}


public class App {
    public static void main(String[] args){
        // Offers thread safe class. In initialization one or more thread can come down the latch.
        CountDownLatch latch = new CountDownLatch(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);

        for(int i=0; i<3; i++){
            executor.submit(new Processor(latch));
        }

        executor.shutdown();

        // Waiting for finishing the latches.
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed.");

    }
}
