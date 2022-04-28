package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input;
        do {
            ManageEventsInCoffeeMachine.displayHint();
            input = scanner.next();
        } while (ManageEventsInCoffeeMachine.processUserInput(input));
    }
}
