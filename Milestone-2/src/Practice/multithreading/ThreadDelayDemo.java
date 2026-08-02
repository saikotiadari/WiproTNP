/*
Create a thread which prints 1 to 10. After printing 5, there should be a delay of 5000 milliseconds before printing 6
*/

public class ThreadDelayDemo implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            if (i == 5) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadDelayDemo());
        thread.start();
    }
}