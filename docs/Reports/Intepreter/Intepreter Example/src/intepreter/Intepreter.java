

import intepreter.Context;
import intepreter.Coordinate;
import intepreter.Player;
import java.util.Scanner;

public class Intepreter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Example: " + Context.EXAMPLE_DESC);
        String command = null;

        while (true) {
            System.out.print("Enter command: ");

            // Get comamnd
            command = sc.nextLine();

            try {
                // parse command to context
                Context context = new Context(command);

                // Call player to interpret
                Player player = context.getPlayer();
                Coordinate newPosition = player.intepret(context);

                System.out.println("Player's new position: " + newPosition);
            } catch (Exception ex) {
                System.out.println("Error: Invalid commad.");
                ex.printStackTrace();
            }

            // Ask for replay
            System.out.println();
            System.out.print("Play again? (press any key to continue, \"q\" to quit)");
            
            command = sc.nextLine();
            if (command.equalsIgnoreCase("q")) {
                break;
            }
        }
    }
}
