public class UserInterface {

    private final Controller controllerTwo = new Controller();

    public void mainMenu() {

        System.out.println("--WEATHER SERVICE--");
        Integer choice = -1;

        while (choice != 0) {
            controllerTwo.printMainMenu();
            try {
                choice = controllerTwo.getUserChoice("Enter your choice:", Integer.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (choice) {
                case 0:
                    break;
                case 1:
                    addingSubmenu();
                    break;
                case 2:
                    controllerTwo.deleteWeatherForGivenCity();
                    break;
                case 3:
                    controllerTwo.updateWeatherForGivenCity();
                    break;
                case 4:
                    displayingSubmenu();
                    break;
                default:
                    System.out.println("*** Enter a number between 0 and 7 ***");
            }
        }
    }

    public void addingSubmenu() {

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
                    break;
                case 1:
                    controllerTwo.addWeatherForGivenCity();
                    break;
                case 2:
                    controllerTwo.addWeatherForCoordinates();
                    break;
                default:
                    System.out.println("*** Enter a number between 0 and 5 ***");
            }
        }
    }

    public void displayingSubmenu() {

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
                    break;
                case 1:
                    controllerTwo.listAllWeathers();
                    break;
                case 2:
                    controllerTwo.findWeatherForGivenWeatherId();
                    break;
                case 3:
                    controllerTwo.findWeatherForGivenCity();
                    break;
                case 4:
                    controllerTwo.findWeatherForGivenCityAndDate();
                    break;
                default:
                    System.out.println("*** Enter a number between 0 and 5 ***");
            }
        }
    }
}
