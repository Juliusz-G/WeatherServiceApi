import dao.WeatherDao;
import entity.Weather;
import jsonModel.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import repository.WeatherRepository;
import repository.WeatherService;

import java.util.InputMismatchException;
import java.util.Scanner;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Controller {

    private final String currentWeatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?" +
            "q=%s&appid=baa6ece140be0985d8bf8766fa381d1d&units=metric";
    private WeatherService weatherService = new WeatherService(new WeatherRepository(), new WeatherDao());

    public void printMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("[1] Adding location to DB.");
        System.out.println("[2] Delete location from DB.");
        System.out.println("[3] Update location from DB.");
        System.out.println("[4] List all locations from DB.");
        System.out.println("[5] Find specific location by ID from DB.");
        System.out.println("[6] Find specific location by city name from DB.");
        System.out.println("[0] EXIT");
        System.out.println("========================================");
    }

    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        try {
            return new Scanner(System.in).nextInt();
        } catch (InputMismatchException e) {
            return getUserChoice();
        }
    }

    public void addWeatherForGivenLocation() {
        System.out.println("Enter a location: ");
        String location = new Scanner(System.in).next();
        WeatherDto weatherDto = weatherService.getWeatherRepository().getWeatherData(String.format(currentWeatherApiUrl, location));
        weatherService.getWeatherDao().save(new Weather(weatherDto));
    }

    public void deleteWeatherForGivenLocation() {
        System.out.println("Enter a location: ");
        String location = new Scanner(System.in).next();
        Weather weatherToDelete = weatherService.getWeatherDao().findByCity(location);
        weatherService.getWeatherDao().delete(weatherToDelete);
    }

//    public void updateWeatherForGivenLocation() {
//        System.out.println("Enter a location: ");
//        String location = new Scanner(System.in).next();
//        Weather weatherToUpdate = weatherService.getWeatherDao().findByCity(location);
//        weatherService.getWeatherDao().update(weatherToUpdate);
//    }

    public void listAllWeathers() {
        System.out.println("List of all locations: ");
        weatherService.getWeatherDao().findAllWeathers().forEach(System.out::println);
    }

    public void listWeatherForGivenLocationId() {
        System.out.println("Enter a location ID: ");
        int locationId = new Scanner(System.in).nextInt();
        System.out.println(weatherService.getWeatherDao().findById(locationId));
    }

    public void listWeatherForGivenLocationCityName() {
        System.out.println("Enter a location city name: ");
        String locationCityName = new Scanner(System.in).next();
        System.out.println(weatherService.getWeatherDao().findByCity(locationCityName));
    }

}

