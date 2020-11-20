/*
 * Build this last.
 * Implement the 3 design patterns in their own classes.
 * Make some sort of interaction interface.
 * launch the whole thing from main.
 * ???
 * refrence: http://www.blackwasp.co.uk/GofPatterns.aspx
 */
package thedpfarm;

import java.util.Scanner;

import thedpfarm.util.CommandProcessor;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in, "UTF-8");
        CommandProcessor cmd = new CommandProcessor();

        while(true) {
            String line;
            if(in.hasNextLine()) {
                line = in.nextLine();
                if(line.equalsIgnoreCase("q")) {
                    in.close();
                    break;
                }
                cmd.process(line);
                line = null;
            }
        }
    }
}
