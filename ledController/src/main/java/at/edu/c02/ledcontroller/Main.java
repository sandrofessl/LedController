package at.edu.c02.ledcontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * This is the main program entry point. TODO: add new commands when implementing additional features.
     */
    public static void main(String[] args) throws IOException {
        LedController ledController = new LedControllerImpl(new ApiServiceImpl());

        String input = "";
        String input1 = "";
        String input2 = "";
        String input3 = "";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!input.equalsIgnoreCase("exit")) {
            System.out.println("=== LED Controller ===");
            System.out.println("Enter 'demo' to send a demo request");
            System.out.println("Enter 'exit' to exit the program");
            input = reader.readLine();
            if (input.equalsIgnoreCase("demo")) {
                ledController.demo();
            }
            if (input.equalsIgnoreCase("groupstatus")) {
                ledController.getGroupLeds();
            }

            if (input.equalsIgnoreCase("status")) {
                System.out.println("Please specify LED");
                input = reader.readLine();
                ledController.getOneLed(Integer.parseInt(input));

            }
            if (input.equalsIgnoreCase("scholtein")) {
                System.out.println("Please specify LED");
                input1 = reader.readLine();
                System.out.println("Please specify Color");
                input2 = reader.readLine();
                System.out.println("Please specify state");
                input3 = reader.readLine();

                ledController.putLight(Integer.parseInt(input1),input2, Boolean.parseBoolean(input3));


            }



        }

    }

}


