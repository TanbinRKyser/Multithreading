// Multithreading 5: Threadpool
package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{

    private int id;

    public Processor(int id) {
        this.id = id;
    }


    @Override
    public void run() {
        System.out.println("Starting... "+id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed. " + id);
    }

}



public class App {
    public static void main(String[] args){

        //Creating a thread pool.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        //long start = System.currentTimeMillis();

        // Taking tasks
        for(int i = 0; i<5; i++){
            executor.submit(new Processor(i));
        }


        // Stop taking tasks
        executor.shutdown();
        System.out.println("All tasks submitted.");


        // Terminating after completing all the tasks.
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");

        //long end = System.currentTimeMillis();
        //System.out.println("Total time: "+ (end-start)/1000.00+"s");

    }
}
