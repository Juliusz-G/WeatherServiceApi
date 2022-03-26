import dao.WeatherDao;
import lombok.extern.log4j.Log4j2;
import model.api.WeatherApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.WeatherRepository;
import service.WeatherService;
import service.WeatherTransformer;
import service.WeatherValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Log4j2
public class Controller {

    private final String API_KEY = "baa6ece140be0985d8bf8766fa381d1d";
    private final String API_URL_CITY =
            "https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&appid=" + API_KEY;
    private final String API_URL_COORDINATES =
            "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&units=metric&appid=" + API_KEY;
    private WeatherService weatherService = new WeatherService(
            new WeatherRepository(),
            new WeatherDao(),
            new WeatherTransformer(),
            new WeatherValidator()
    );
    private final WeatherValidator weatherValidator = new WeatherValidator();

    //-----Menu structure-----

    //Main menu
    public void printMainMenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Add weather (Submenu).");
        System.out.println("[2] Delete weather (Submenu).");
        System.out.println("[3] Update weather for city.");
        System.out.println("[4] Display weather (Submenu).");
        System.out.println("[0] EXIT");
        System.out.println("==========================================================");
    }

    //Submenu for displaying
    public void printDisplayingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Display all weathers.");
        System.out.println("[2] Display weather by ID.");
        System.out.println("[3] Display weather by city.");
        System.out.println("[4] Display weather by coordinates.");
        System.out.println("[5] Display weather by city and date.");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Submenu for adding
    public void printAddingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Add by city.");
        System.out.println("[2] Add by coordinates.");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Submenu for deleting
    public void printDeletingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Delete by city.");
        System.out.println("[2] Delete by ID.");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Gets user input (Any type)
    public <T> T getUserChoice(String message, Class<T> c) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        try {
            if (c == Integer.class)
                return c.cast(scanner.nextInt());
            if (c == String.class)
                return c.cast(scanner.nextLine());
        } catch (InputMismatchException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    //-----Methods available in main menu-----

    //[2] Delete 5-day weather for given city
    public void deleteWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter city name: ", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        weatherService.deleteWeatherForGivenCity(cityName);
        System.out.println(cityName + " successfully deleted!");
    }

    public void deleteWeatherForId() {
        Integer weatherId = null;

        try {
            weatherId = getUserChoice("Enter weather ID: ", Integer.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        weatherService.deleteWeatherForGivenId(weatherId);
        System.out.println(weatherId + " successfully deleted!");
    }

    //[3] Update 5-day weather for given city
    public void updateWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter city name: ", String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        weatherService.updateWeatherForGivenCity(cityName);
        System.out.println(cityName + " successfully updated!");
    }

    //-----Methods available in submenu ([1] Add 5-day weather)-----

    //[1] Add by city
    public void addWeatherForGivenCity() {
        String cityName = null;
        String year;
        String month;
        String day;
        String date = null;
        try {
            cityName = getUserChoice("Enter city name: ", String.class);
            System.out.printf("SELECT DATE BETWEEN %s AND %s", LocalDate.now(), LocalDate.now().plusDays(5)).println();
            year = getUserChoice("Enter year: ", String.class);
            month = getUserChoice("Enter month: ", String.class);
            day = getUserChoice("Enter day: ", String.class);
            date = String.format("%s/%s/%s", year, month, day);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String resultDate = weatherValidator.addWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String resultCityName = weatherValidator.cityNameValidation(cityName) ? cityName : "";
        WeatherApi weatherApi = weatherService.addWeatherForGivenCity(API_URL_CITY, resultCityName, resultDate);
        System.out.println(weatherApi != null ? weatherApi.getCity().getName() + " successfully added!" :
                " cannot find city!");
    }

    //[2] Add by coordinates
    public void addWeatherForCoordinates() {
        String lon = null;
        String lat = null;
        String year;
        String month;
        String day;
        String date = null;
        try {
            lon = getUserChoice("Enter lon: ", String.class);
            lat = getUserChoice("Enter lat: ", String.class);
            year = getUserChoice("Enter year: ", String.class);
            month = getUserChoice("Enter month: ", String.class);
            day = getUserChoice("Enter day: ", String.class);
            date = String.format("%s/%s/%s", year, month, day);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String resultDate = weatherValidator.addWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String longitude = weatherValidator.longitudeValidation(lon) ? lon : "";
        String latitude = weatherValidator.latitudeValidation(lat) ? lat : "";
        WeatherApi weatherApi = weatherService.addWeatherForCoordinates(API_URL_COORDINATES, longitude, latitude, resultDate);
        System.out.println(weatherApi != null ? weatherApi.getCity().getName() + " successfully added!" :
                " cannot find city!");
    }

    //-----Methods available in submenu ([4] Display weather)-----

    public void listAllWeathers() {
        System.out.println("List of all weathers: ");
        weatherService.listAllWeathers().forEach(System.out::println);
    }

    public void findWeatherForGivenWeatherId() {
        Integer weatherId = null;

        try {
            weatherId = getUserChoice("Enter weather ID: ", Integer.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        System.out.printf("Weather for weatherId = %s:%n", weatherId);
        System.out.println(weatherService.findWeatherForGivenWeatherId(weatherId));
    }

    public void findWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter city name: ", String.class);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        System.out.printf("Weather for %s:%n", cityName);
        String resultCityName = weatherValidator.cityNameValidation(cityName) ? cityName : "";
        weatherService.findWeatherForGivenCity(resultCityName).forEach(System.out::println);
    }

    public void findWeatherForGivenCityAndDate() {
        displayDistinctCityNames();
        String cityName = null;
        String year;
        String month;
        String day;
        String date = null;

        try {
            cityName = getUserChoice("Enter city name: ", String.class);
            year = getUserChoice("Enter year: ", String.class);
            month = getUserChoice("Enter month: ", String.class);
            day = getUserChoice("Enter day: ", String.class);
            date = String.format("%s/%s/%s", year, month, day);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String resultCityName = weatherValidator.cityNameValidation(cityName) ? cityName : "";
        weatherService.findWeatherForGivenCityAndDate(resultCityName, resultDate).forEach(System.out::println);
    }

    public void findWeatherForGivenCoordinatesAndDate() {
        displayDistinctCoordinates();
        String lon = null;
        String lat = null;
        String year;
        String month;
        String day;
        String date = null;

        try {
            lon = getUserChoice("Enter lon: ", String.class);
            lat = getUserChoice("Enter lat: ", String.class);
            year = getUserChoice("Enter year: ", String.class);
            month = getUserChoice("Enter month: ", String.class);
            day = getUserChoice("Enter day: ", String.class);
            date = String.format("%s/%s/%s", year, month, day);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String longitude = weatherValidator.longitudeValidation(lon) ? lon : "";
        String latitude = weatherValidator.latitudeValidation(lon) ? lon : "";
        System.out.printf("Weather for %s %s %s:%n", lon, lat, date);
        weatherService.findWeatherForGivenCoordinatesAndDate(longitude, latitude, resultDate)
                .forEach(System.out::println);
    }


    //-----Additional methods-----

    private void displayDistinctCityNames() {
        System.out.println("Available cities: ");
        System.out.println(weatherService.displayDistinctCityNames());
    }

    private void displayDistinctCoordinates() {
        System.out.println("Available coordinates: ");
        System.out.println(weatherService.displayDistinctCoordinates());
    }

}
