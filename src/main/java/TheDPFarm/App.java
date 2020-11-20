package thedpfarm;

import java.util.Scanner;
import thedpfarm.util.CommandProcessor;

/**
 * refrence: http://www.blackwasp.co.uk/GofPatterns.aspx.
 */
public class App {
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
    }
}
