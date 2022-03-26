package gui;

public class UserInterface {

    private final Controller controller = new Controller();

    public void mainMenu() {

        System.out.println("""
                \033[4;34m
                --------------WELCOME TO WEATHER SERVICE APP--------------
                \033[0m""");
        Integer choice = -1;

        while (choice != 0) {
            System.out.println("\033[1;34m" + "------------------------MAIN MENU-------------------------" + "\033[0m");
            controller.printMainMenu();
            try {
                choice = controller.getUserChoice("Enter your choice:", Integer.class);
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
                    System.out.println("\033[1;34m" + "TO DELETING SUBMENU -->" + "\033[0m");
                    deletingSubmenu();
                    break;
                case 3:
                    controller.updateWeatherForGivenCity();
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
            controller.printAddingSubmenu();
            try {
                choice = controller.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "TO MAIN MENU -->" + "\033[0m");
                    break;
                case 1:
                    controller.addWeatherForGivenCity();
                    return;
                case 2:
                    controller.addWeatherForCoordinates();
                    return;
                default:
                    System.out.println("*** Enter a number between 0 and 2 ***");
            }
        }
    }

    public void deletingSubmenu() {

        System.out.println("\033[1;34m" + "-------------------DELETE WEATHER SUBMENU-----------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controller.printDeletingSubmenu();
            try {
                choice = controller.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "TO MAIN MENU -->" + "\033[0m");
                    break;
                case 1:
                    controller.deleteWeatherForGivenCity();
                    return;
                case 2:
                    controller.deleteWeatherForId();
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
            controller.printDisplayingSubmenu();
            try {
                choice = controller.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34m" + "TO MAIN MENU -->" + "\033[0m");
                    break;
                case 1:
                    controller.listAllWeathers();
                    return;
                case 2:
                    controller.findWeatherForGivenWeatherId();
                    return;
                case 3:
                    controller.findWeatherForGivenCity();
                    return;
                case 4:
                    controller.findWeatherForGivenCoordinatesAndDate();
                    return;
                case 5:
                    controller.findWeatherForGivenCityAndDate();
                    return;
                default:
                    System.out.println("*** Enter a number between 0 and 5 ***");
            }
        }
    }
}
