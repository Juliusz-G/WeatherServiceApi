public class UserInterface {

    private final Controller controllerTwo = new Controller();

    public void mainMenu() {

        System.out.println("""
                \033[4;34m
                --------------WELCOME TO WEATHER SERVICE APP--------------
                \033[0m""");
        Integer choice = -1;

        while (choice != 0) {
            System.out.println("\033[1;34m" + "------------------------MAIN MENU-------------------------" + "\033[0m");
            controllerTwo.printMainMenu();
            try {
                choice = controllerTwo.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "CLOSING... THANK YOU!" + "\033[0m");
                    break;
                case 1:
                    System.out.println("\033[1;34m" + "TO ADDING SUBMENU -->" + "\033[0m");
                    addingSubmenu();
                    break;
                case 2:
                    controllerTwo.deleteWeatherForGivenCity();
                    break;
                case 3:
                    controllerTwo.updateWeatherForGivenCity();
                    break;
                case 4:
                    System.out.println("\033[1;34m" + "TO DISPLAYING SUBMENU -->" + "\033[0m");
                    displayingSubmenu();
                    break;
                default:
                    System.out.println("*** Enter a number between 0 and 4 ***");
            }
        }
    }

    public void addingSubmenu() {

        System.out.println("\033[1;34m" + "--------------------ADD WEATHER SUBMENU-------------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controllerTwo.printAddingSubmenu();
            try {
                choice = controllerTwo.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "TO MAIN MENU -->" + "\033[0m");
                    break;
                case 1:
                    controllerTwo.addWeatherForGivenCity();
                    return;
                case 2:
                    controllerTwo.addWeatherForCoordinates();
                    return;
                default:
                    System.out.println("*** Enter a number between 0 and 2 ***");
            }
        }
    }

    public void displayingSubmenu() {

        System.out.println("\033[1;34m" + "-----------------DISPLAY WEATHER SUBMENU------------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controllerTwo.printDisplayingSubmenu();
            try {
                choice = controllerTwo.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "TO MAIN MENU -->" + "\033[0m");
                    break;
                case 1:
                    controllerTwo.listAllWeathers();
                    return;
                case 2:
                    controllerTwo.findWeatherForGivenWeatherId();
                    return;
                case 3:
                    controllerTwo.findWeatherForGivenCity();
                    return;
                case 4:
                    controllerTwo.findWeatherForGivenCoordinatesAndDate();
                    return;
                case 5:
                    controllerTwo.findWeatherForGivenCityAndDate();
                    return;
                default:
                    System.out.println("*** Enter a number between 0 and 5 ***");
            }
        }
    }
}
