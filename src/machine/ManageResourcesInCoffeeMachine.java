package machine;

public class ManageResourcesInCoffeeMachine {
    static CoffeeFromMachine resourcesAvailable = new CoffeeFromMachine(400, 540, 120, 9, 550);
    static CoffeeFromMachine espresso = new CoffeeFromMachine(250, 0, 16, 1, 4);
    static CoffeeFromMachine latte = new CoffeeFromMachine(350, 75, 20, 1, 7);
    static CoffeeFromMachine cappuccino = new CoffeeFromMachine(200, 100, 12, 1, 6);

    public static void remaining() {
        System.out.printf("The coffee machine has: %n" +
                        "%d ml of water %n" +
                        "%d ml of milk %n" +
                        "%d g of coffee beans %n" +
                        "%d disposable cups %n" +
                        "$%d of money %n",
                resourcesAvailable.amountOfWater,
                resourcesAvailable.amountOfMilk,
                resourcesAvailable.coffeeBeans,
                resourcesAvailable.countOfCups,
                resourcesAvailable.money
        );
    }


    public static void buy(String number) {
        switch (number) {
            case "1":
                makeCoffee(espresso);
                break;
            case "2":
                makeCoffee(latte);
                break;
            case "3":
                makeCoffee(cappuccino);
                break;
            default:
                break;
        }
    }

    private static void makeCoffee(CoffeeFromMachine coffee) {
        if (resourcesAvailable.amountOfWater >= coffee.amountOfWater &&
                resourcesAvailable.amountOfMilk >= coffee.amountOfMilk &&
                resourcesAvailable.coffeeBeans >= coffee.coffeeBeans &&
                resourcesAvailable.countOfCups >= coffee.countOfCups) {

            resourcesAvailable.amountOfWater -= coffee.amountOfWater;
            resourcesAvailable.amountOfMilk -= coffee.amountOfMilk;
            resourcesAvailable.coffeeBeans -= coffee.coffeeBeans;
            resourcesAvailable.countOfCups -= coffee.countOfCups;

            resourcesAvailable.money += coffee.money;

            System.out.println("I have enough resources, making you a coffee!");
        } else {
            if (resourcesAvailable.amountOfWater < coffee.amountOfWater) {
                System.out.println("Sorry, not enough water!");
            } else if (resourcesAvailable.amountOfMilk < coffee.amountOfMilk) {
                System.out.println("Sorry, not enough coffee milk!");
            } else if (resourcesAvailable.coffeeBeans < coffee.coffeeBeans) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (resourcesAvailable.countOfCups < coffee.countOfCups) {
                System.out.println("Sorry, not enough disposable cups!");
            }
        }
    }


    enum FillChoice {
        WATER, MILK, COFFEE, CUP
    }

    private static FillChoice fillChoice = FillChoice.WATER;

    public static void fill(String number) {

        switch (fillChoice) {
            case WATER:
                resourcesAvailable.amountOfWater += Integer.parseInt(number);
                fillChoice = FillChoice.MILK; //transfer
                break;

            case MILK:
                resourcesAvailable.amountOfMilk += Integer.parseInt(number);
                fillChoice = FillChoice.COFFEE;
                break;

            case COFFEE:
                resourcesAvailable.coffeeBeans += Integer.parseInt(number);
                fillChoice = FillChoice.CUP;
                break;

            case CUP:
                resourcesAvailable.countOfCups += Integer.parseInt(number);
                break;
            default:
                break;
        }
    }


    public static void take() {
        System.out.printf("I gave you $%d %n", resourcesAvailable.money);
        resourcesAvailable.money = 0;
    }
}
