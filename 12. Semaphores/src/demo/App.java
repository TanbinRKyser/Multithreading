/*
    Multithreading 12 : Semaphores

    It uses basically to control access to some resources. For example 2 threads are trying to use the same resource at
    the same time and one has acquired the resource it will lock that resource. By using semaphores we can revoke the per-
    mission and lock this.
 */

package demo;

import java.util.concurrent.Semaphore;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        // Release is another way of giving permission
        semaphore.release();

        // And to revoke a permission
        semaphore.acquire();

        System.out.println("Available permits : " + semaphore.availablePermits());
    }
}
