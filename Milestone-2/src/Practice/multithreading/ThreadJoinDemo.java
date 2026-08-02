/*
Create two threads, one thread to display all even numbers between 1 & 20, another to display odd numbers between 1 & 20.
Note: Display all even numbers followed by odd numbers
Hint: use join
*/

class EvenNumbersThread extends Thread {
    @Override
    public void run() {
        System.out.println("Even Numbers between 1 and 20:");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

class OddNumbersThread extends Thread {
    @Override
    public void run() {
        System.out.println("Odd Numbers between 1 and 20:");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}

public class ThreadJoinDemo {
    public static void main(String[] args) {
        EvenNumbersThread evenThread = new EvenNumbersThread();
        OddNumbersThread oddThread = new OddNumbersThread();
        evenThread.start();

        try {
            evenThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
        oddThread.start();
    }
}