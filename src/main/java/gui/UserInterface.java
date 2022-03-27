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
            System.out.println("\033[1;34m------------------------MAIN MENU-------------------------\033[0m");
            controller.printMainMenu();
            try {
                choice = controller.getUserChoice("ENTER YOUR CHOICE:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34mCLOSING... THANK YOU!\033[0m");
                    break;
                case 1:
                    System.out.println("\033[1;34mTO ADDING SUBMENU -->\033[0m");
                    addingSubmenu();
                    break;
                case 2:
                    System.out.println("\033[1;34mTO DELETING SUBMENU -->\033[0m");
                    deletingSubmenu();
                    break;
                case 3:
                    controller.updateWeatherForGivenCity();
                    break;
                case 4:
                    System.out.println("\033[1;34mTO DISPLAYING SUBMENU -->\033[0m");
                    displayingSubmenu();
                    break;
                default:
                    System.out.println("\033[1;34m*** ENTER A NUMBER BETWEEN 0 AND 4 ***\033[0m");
            }
        }
    }

    public void addingSubmenu() {

        System.out.println("\033[1;34m" + "--------------------ADD WEATHER SUBMENU-------------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controller.printAddingSubmenu();
            try {
                choice = controller.getUserChoice("ENTER YOUR CHOICE:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34mTO MAIN MENU -->\033[0m");
                    break;
                case 1:
                    controller.addWeatherForGivenCity();
                    return;
                case 2:
                    controller.addWeatherForCoordinates();
                    return;
                default:
                    System.out.println("\033[1;34m*** ENTER A NUMBER BETWEEN 0 AND 2 ***\033[0m");
            }
        }
    }

    public void deletingSubmenu() {

        System.out.println("\033[1;34m" + "-------------------DELETE WEATHER SUBMENU-----------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controller.printDeletingSubmenu();
            try {
                choice = controller.getUserChoice("ENTER YOUR CHOICE:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34mTO MAIN MENU -->\033[0m");
                    break;
                case 1:
                    controller.deleteWeatherForGivenCity();
                    return;
                case 2:
                    controller.deleteWeatherForId();
                    return;
                default:
                    System.out.println("\033[1;34m*** ENTER A NUMBER BETWEEN 0 AND 2 ***\033[0m");
            }
        }
    }

    public void displayingSubmenu() {

        System.out.println("\033[1;34m" + "-----------------DISPLAY WEATHER SUBMENU------------------" + "\033[0m");
        Integer choice = -1;

        while (choice != 0) {
            controller.printDisplayingSubmenu();
            try {
                choice = controller.getUserChoice("ENTER YOUR CHOICE:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    System.out.println("\033[1;34mTO MAIN MENU -->\033[0m");
                    break;
                case 1:
                    controller.displayAllWeathers();
                    return;
                case 2:
                    controller.displayWeatherForGivenWeatherId();
                    return;
                case 3:
                    controller.displayWeatherForGivenCity();
                    return;
                case 4:
                    controller.displayWeatherForGivenCoordinatesAndDate();
                    return;
                case 5:
                    controller.displayWeatherForGivenCityAndDate();
                    return;
                default:
                    System.out.println("\033[1;34m*** ENTER A NUMBER BETWEEN 0 AND 5 ***\033[0m");
            }
        }
    }
}
