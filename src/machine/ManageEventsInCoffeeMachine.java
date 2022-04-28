package machine;

public class ManageEventsInCoffeeMachine {

    enum EventOfCoffeeMachine {
        MENU, COFFEE_SELECTION, FILL
    }

    private static EventOfCoffeeMachine event = EventOfCoffeeMachine.MENU;

    public static boolean processUserInput(String input) {
        switch (event) {

            case MENU:
                if (input.equals("exit")) {
                    return false;
                } else {
                    selectFromMenu(input);
                    return true;
                }

            case COFFEE_SELECTION:
                ManageResourcesInCoffeeMachine.buy(input);
                event = EventOfCoffeeMachine.MENU; //transfer
                return true;

            case FILL:
                ManageResourcesInCoffeeMachine.fill(input);
                if (isLastFill) {
                    event = EventOfCoffeeMachine.MENU;
                }
                return true;

            default:
                System.out.println("Incorrect work");
                return false;
        }
    }


    private static void selectFromMenu(String selection) {
        switch (selection) {
            case "buy":
                event = EventOfCoffeeMachine.COFFEE_SELECTION;
                break;
            case "fill":
                event = EventOfCoffeeMachine.FILL;
                break;
            case "take":
                ManageResourcesInCoffeeMachine.take();
                break;
            case "remaining":
                ManageResourcesInCoffeeMachine.remaining();
                break;
            default:
                break;
        }
    }


    public static void displayHint() {
        switch (event) {
            case MENU:
                System.out.printf("Write action (buy, fill, take, remaining, exit): %n> ");
                break;
            case COFFEE_SELECTION:
                System.out.printf("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, " +
                        "back - to main menu: %n> ");
                break;
            case FILL:
                displayFill();
                break;
            default:
                break;
        }
    }


    enum FillChoice {
        WATER, MILK, COFFEE, CUP
    }

    private static FillChoice fillChoice = FillChoice.WATER;
    private static boolean isLastFill = false; //transfer

    private static void displayFill() {
        switch (fillChoice) {
            case WATER:
                System.out.printf("Write how many ml of water you want to add: %n> ");
                fillChoice = FillChoice.MILK; //transfer
                break;
            case MILK:
                System.out.printf("Write how many ml of milk you want to add: %n> ");
                fillChoice = FillChoice.COFFEE;
                break;
            case COFFEE:
                System.out.printf("Write how many grams of coffee beans you want to add: %n> ");
                fillChoice = FillChoice.CUP;
                break;
            case CUP:
                System.out.printf("Write how many disposable cups of coffee you want to add: %n> ");
                isLastFill = true;
                break;
            default:
                break;
        }
    }

}
