import dao.WeatherDao;
import repository.WeatherRepository;
import repository.WeatherService;

public class UserInterface {

    private final Controller controller = new Controller();

    public void menu() {

        System.out.println("--WEATHER SERVICE--");
        int choice = -1;

        while (choice != 0) {
            controller.printMenu();
            choice = controller.getUserChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    controller.addWeatherForGivenLocation();
                    break;
                case 2:
                    controller.deleteWeatherForGivenLocation();
                    break;
                case 3:
//                    controller.updateWeatherForGivenLocation();
                    break;
                case 4:
                    controller.listAllWeathers();
                    break;
                case 5:
                    controller.listWeatherForGivenLocationId();
                    break;
                case 6:
                    controller.listWeatherForGivenLocationCityName();
                    break;
                default:
                    System.out.println("*** Enter a number between 0 and 5 ***");
            }
        }
    }
}
