/*
store colours in the form of an array
ex: String colours[]={"white","blue","black","green","red","yellow"};
display all colours repeatedly by generating colour index from Random class. If the random colour index matches to red stop display.
Note: perform this task by implementing Runnable interface
*/

import java.util.Random;

public class ColourDisplay implements Runnable {

    @Override
    public void run() {
        String[] colours = {"white", "blue", "black", "green", "red", "yellow"};
        Random random = new Random();

        while (true) {
            int index = random.nextInt(colours.length);
            String selectedColour = colours[index];

            System.out.println("Generated Colour: " + selectedColour);

            if ("red".equalsIgnoreCase(selectedColour)) {
                System.out.println("'red' encountered. Stopping display.");
                break;
            }

            try {
                Thread.sleep(500); // Small delay for readability during execution
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public static void main(String[] args) {
        ColourDisplay colourTask = new ColourDisplay();
        Thread thread = new Thread(colourTask);
        thread.start();
    }
}