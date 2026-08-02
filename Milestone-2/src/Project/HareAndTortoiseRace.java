/*
Mini-Project 1: Hare and Tortoise Race

a. Create two threads named 'Hare' and 'Tortoise'. The threads created should run 100 meters 
   and the thread which finishes first 'wins the race'. When one of the threads wins the race, 
   stop the second thread.
b. Since the Hare is faster than Tortoise, set a high priority to Hare thread and observe the results.
c. Modify the program so that the 'Hare' thread 'sleeps' for 1000 milliseconds after running 60 meters. 
   Observe which thread wins the race.
*/

class RaceRunner implements Runnable {
    private static boolean raceWon = false;

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        for (int distance = 1; distance <= 100; distance++) {
            if (raceWon) {
                System.out.println("--> " + threadName + " stopped because the race was won by someone else.");
                return;
            }

            System.out.println(threadName + " has run " + distance + " meters.");
            if ("Hare".equalsIgnoreCase(threadName) && distance == 60) {
                try {
                    System.out.println("=== Hare is sleeping for 1000ms at 60 meters! ===");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Hare was interrupted during sleep.");
                }
            }
            if (distance == 100 && !raceWon) {
                synchronized (RaceRunner.class) {
                    if (!raceWon) {
                        raceWon = true;
                        System.out.println("\n=================================");
                        System.out.println(" " + threadName.toUpperCase() + " WINS THE RACE! ");
                        System.out.println("=================================\n");
                    }
                }
            }
        }
    }
}

public class HareAndTortoiseRace {
    public static void main(String[] args) {
        RaceRunner runner = new RaceRunner();

        Thread hare = new Thread(runner, "Hare");
        Thread tortoise = new Thread(runner, "Tortoise");
        hare.setPriority(Thread.MAX_PRIORITY);    
        tortoise.setPriority(Thread.MIN_PRIORITY);  
        hare.start();
        tortoise.start();
    }
}