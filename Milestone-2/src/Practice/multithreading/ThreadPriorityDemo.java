/*
Create three threads- with different priorities – MAX, MIN, NORM- and start the threads at the same time. 
Observe the completion of the threads.
*/

class PriorityRunner implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        
        System.out.println("Thread [" + threadName + "] started with priority: " + priority);
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread [" + threadName + "] - Count: " + i);
        }

        System.out.println("--> Thread [" + threadName + "] COMPLETED.");
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        PriorityRunner runner = new PriorityRunner();

        Thread t1 = new Thread(runner, "MinPriorityThread");
        Thread t2 = new Thread(runner, "NormPriorityThread");
        Thread t3 = new Thread(runner, "MaxPriorityThread");
        t1.setPriority(Thread.MIN_PRIORITY); 
        t2.setPriority(Thread.NORM_PRIORITY); 
        t3.setPriority(Thread.MAX_PRIORITY);  
        t1.start();
        t2.start();
        t3.start();
    }
}