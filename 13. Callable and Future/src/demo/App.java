/*
    Multithreading : Callable and Future

 */
package demo;

import java.util.Random;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();

        // In the executors submit function we have used a Callable class and its implemented method Call(). Both Callable
        // and Future class used the same data types as its parameter.
        Future<Integer> future = executors.submit( () -> {
            Random random = new Random();
            int duration = random.nextInt(4000);
             {
                System.out.println("Starting...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");
            }
            return duration;
        }); //End of call function


        // Shutting down executors.
        executors.shutdown();

        // Waiting to finish all the threads.
        executors.awaitTermination(1, TimeUnit.DAYS);

        // Showing the total time threads slept.
        try {
            System.out.println("Total duration: " + future.get()/1000.00 +"s");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception.");
        } catch (ExecutionException e) {
            System.out.println("Execution Exception.");
        }
    }
}
