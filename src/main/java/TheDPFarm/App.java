/*
 * Build this last.
 * Implement the 3 design patterns in their own classes.
 * Make some sort of interaction interface.
 * launch the whole thing from main.
 * ???
 */
package TheDPFarm;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
