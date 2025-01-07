package lab01;

import java.io.IOException;

public class WSSolver {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Usage: java lab01.WSSolver <input>");
            System.exit(1);
        }

        String WordSoupFileName = args[0];
        String WordSoupResultFileName = args[0].replaceAll(".txt", "_result.txt");

        try {
            new WSSolverProcessing(WordSoupFileName, WordSoupResultFileName);
        } catch (IOException e) {
            System.out.println("ERROR: Impossible to create an instance of WSSolveProcessing: " + e.getMessage());
        }
    }
}
