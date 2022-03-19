import dao.WeatherDao;
import model.entity.WeatherEntity;
import model.api.WeatherApi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.WeatherRepository;
import service.WeatherService;
import service.WeatherTransformer;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Controller {

    private final String API_KEY = "baa6ece140be0985d8bf8766fa381d1d";
    private final String API_URL_CITY =
            "https://api.openweathermap.org/data/2.5/forecast?q=%s&units=metric&appid=" + API_KEY;
    private final String API_URL_COORDINATES =
            "https://api.openweathermap.org/data/2.5/forecast?lat=%s&lon=%s&units=metric&appid=" + API_KEY;
    private WeatherService weatherService = new WeatherService(
            new WeatherRepository(),
            new WeatherDao(),
            new WeatherTransformer()
    );

    //-----Menu structure-----

    //Main menu
    public void printMainMenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Add 5-day weather for city.");
        System.out.println("[2] Delete 5-day weather for city.");
        System.out.println("[3] Update 5-day weather for city.");
        System.out.println("[4] Display weather (Submenu).");
        System.out.println("[0] EXIT");
        System.out.println("==========================================================");
    }

    //Submenu
    public void printSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] Display all weathers.");
        System.out.println("[2] Display weather by ID.");
        System.out.println("[3] Display weather by city.");
        System.out.println("[4] Display weather by city and date.");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Gets user input (Any type)
    public <T> T getUserChoice(String message, Class<T> c) throws Exception {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        try {
            if (c == Integer.class)
                return c.cast(scanner.nextInt());
            if (c == String.class)
                return c.cast(scanner.nextLine());
        } catch (InputMismatchException e) {
            throw new Exception(e);
        }
        return null;
    }

    //-----Methods available in main menu-----

    //[1] Add 5-day weather for given city
    public void addWeatherForGivenCity() {
        String cityName = null;

        try {
            cityName = getUserChoice("Enter a location: ", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WeatherApi weatherApi = weatherService.getWeatherRepository()
                .jsonDeserialization(String
                        .format(API_URL_CITY, cityName)
                );

        weatherService.getWeatherTransformer()
                .fromDtoToEntity(weatherService.getWeatherTransformer()
                        .fromApiToDto(weatherApi)
                )
                .forEach(w -> weatherService
                        .getWeatherDao()
                        .saveOrUpdate(w) //if exists than update
                );
        System.out.println(cityName + " successfully added!");
    }

    //[2] Delete 5-day weather for given city
    public void deleteWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter a location: ", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        weatherService.getWeatherDao()
                .findByCity(cityName)
                .forEach(w -> weatherService
                        .getWeatherDao()
                        .delete(w)
                );
        System.out.println(cityName + " successfully deleted!");
    }

    //[3] Update 5-day weather for given city
    public void updateWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter a location: ", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        weatherService.getWeatherDao()
                .findByCity(cityName)
                .forEach(w -> weatherService
                        .getWeatherDao()
                        .update(w)
                );
        System.out.println(cityName + " successfully updated!");
    }

    //-----Methods available in submenu ([4] Display weather (Submenu))-----

    public void listAllWeathers() {
        System.out.println("List of all weathers: ");
        weatherService.getWeatherDao()
                .findAllWeathers()
                .forEach(System.out::println);
    }

    public void findWeatherForGivenWeatherId() {
        Integer weatherId = null;

        try {
            weatherId = getUserChoice("Enter a location: ", Integer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("Weather for weatherId = %s:%n", weatherId);
        System.out.println(weatherService.getWeatherDao()
                .findById(weatherId)
        );
    }

    public void findWeatherForGivenCity() {
        displayDistinctCityNames();
        String cityName = null;

        try {
            cityName = getUserChoice("Enter a location: ", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.printf("Weather for %s:%n", cityName);

        weatherService.getWeatherDao()
                .findByCity(cityName)
                .forEach(System.out::println);
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
            e.printStackTrace();
        }

        System.out.printf("Weather for %s %s:%n", cityName, date);

        weatherService.getWeatherDao()
                .findByCityAndDate(cityName, date)
                .forEach(System.out::println);
    }

    //-----Additional methods-----

    private void displayDistinctCityNames() {
        System.out.println("Available cities: ");
        weatherService.getWeatherDao()
                .findAllWeathers()
                .stream()
                .filter(distinctByKey(WeatherEntity::getCityName))
                .map(WeatherEntity::getCityName)
                .forEach(System.out::println);
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
