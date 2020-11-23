package thedpfarm;

import java.util.Scanner;
import thedpfarm.util.CommandProcessor;

public class App {
    /**
     * Main method for running the simulator. 
     * @param args Accepts no arguments.
     */
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in, "UTF-8");
        CommandProcessor cmd = new CommandProcessor();

        while (true) {
            String line;
            if (in.hasNextLine()) {
                line = in.nextLine();
                if (line.equalsIgnoreCase("q")) {
                    in.close();
                    break;
                }
                cmd.process(line);
                line = null;
            }
        }
        System.exit(0);
    }
}
