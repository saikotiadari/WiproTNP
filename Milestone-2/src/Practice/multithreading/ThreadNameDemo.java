/*
Create two threads and assign names ‘Scooby’ and ‘Shaggy’ to the two threads. Display both thread names.
*/

public class ThreadNameDemo implements Runnable {

    @Override
    public void run() {
        System.out.println("Running Thread Name: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        ThreadNameDemo runnable = new ThreadNameDemo();

        Thread t1 = new Thread(runnable, "Scooby");
        Thread t2 = new Thread(runnable, "Shaggy");

        t1.start();
        t2.start();
    }
}