package gui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import model.api.WeatherApi;
import service.WeatherService;
import validation.WeatherValidator;

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
    private WeatherService weatherService = new WeatherService();
    private final WeatherValidator weatherValidator = new WeatherValidator();

    //---------------------------------------------Displaying Menu Methods----------------------------------------------

    //Main menu
    public void printMainMenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] ADD WEATHER (SUBMENU)");
        System.out.println("[2] DELETE WEATHER (SUBMENU)");
        System.out.println("[3] UPDATE WEATHER FOR CITY");
        System.out.println("[4] DISPLAY WEATHER (SUBMENU)");
        System.out.println("[0] EXIT");
        System.out.println("==========================================================");
    }

    //Displaying Submenu
    public void printDisplayingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] DISPLAY ALL WEATHERS");
        System.out.println("[2] DISPLAY WEATHER BY ID");
        System.out.println("[3] DISPLAY WEATHER BY CITY");
        System.out.println("[4] DISPLAY WEATHER BY COORDINATES");
        System.out.println("[5] DISPLAY WEATHER BY CITY AND DATE");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Adding Submenu
    public void printAddingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] ADD BY CITY");
        System.out.println("[2] ADD BY COORDINATES");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Deleting Submenu
    public void printDeletingSubmenu() {
        System.out.println();
        System.out.println("==========================================================");
        System.out.println("[1] DELETE BY CITY");
        System.out.println("[2] DELETE BY ID");
        System.out.println("[0] BACK");
        System.out.println("==========================================================");
    }

    //Gets user input
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
        return (T) getUserChoice("ENTER AGAIN: ", Integer.class);
    }

    //-------------------------------------------------Deleting Methods-------------------------------------------------

    //Delete for city
    public void deleteWeatherForGivenCity() {
        if (!displayDistinctCityNames()) {
            return;
        }

        String cityName = getUserChoice("ENTER CITY NAME: ", String.class);

        if (weatherService.findDistinctCityNames().contains(cityName)) {
            weatherService.deleteWeatherForGivenCity(cityName);
            System.out.println(cityName + " SUCCESSFULLY DELETED!");
        } else {
            System.out.println("CITY NAMED \"" + cityName + "\" DON'T EXISTS!");
        }
    }

    //Delete for id
    public void deleteWeatherForId() {
        if (!displayAllIds()) {
            return;
        }

        Integer weatherId = getUserChoice("ENTER WEATHER ID: ", Integer.class);
        int listSizeBeforeDelete = weatherService.getWeatherDao().findAllWeathers().size();

        weatherService.deleteWeatherForGivenId(weatherId);

        if (listSizeBeforeDelete > weatherService.getWeatherDao().findAllWeathers().size()) {
            System.out.println(weatherId + " SUCCESSFULLY DELETED!");
        } else {
            System.out.println("CITY WITH ID \"" + weatherId + "\" DON'T EXISTS!");
        }
    }

    //-------------------------------------------------Updating Methods-------------------------------------------------

    //Update for city
    public void updateWeatherForGivenCity() {
        if (!displayDistinctCityNames()) {
            return;
        }

        String cityName = getUserChoice("ENTER CITY NAME: ", String.class);

        weatherService.updateWeatherForGivenCity(cityName);
        System.out.println(cityName + " SUCCESSFULLY UPDATED!");
    }

    //--------------------------------------------------Adding Methods--------------------------------------------------

    //Add for city
    public void addWeatherForGivenCity() {
        String cityName = getUserChoice("ENTER CITY NAME: ", String.class);
        System.out.printf("SELECT DATE BETWEEN %s AND %s", LocalDate.now(), LocalDate.now().plusDays(5)).println();
        String year = getUserChoice("ENTER YEAR: ", String.class);
        String month = getUserChoice("ENTER MONTH: ", String.class);
        String day = getUserChoice("ENTER DAY: ", String.class);
        String date = String.format("%s/%s/%s", year, month, day);

        String resultDate = weatherValidator.addWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String resultCityName = weatherValidator.cityNameValidation(cityName) ? cityName : "";
        WeatherApi weatherApi = weatherService.addWeatherForGivenCity(API_URL_CITY, resultCityName, resultDate);
        System.out.println(weatherApi != null ? weatherApi.getCity().getName() + " SUCCESSFULLY ADDED!" :
                "CANNOT FIND CITY!");
    }

    //Add for coordinates
    public void addWeatherForCoordinates() {
        String lon = getUserChoice("ENTER LON: ", String.class);
        String lat = getUserChoice("ENTER LAT: ", String.class);
        String year = getUserChoice("ENTER YEAR: ", String.class);
        String month = getUserChoice("ENTER MONTH: ", String.class);
        String day = getUserChoice("ENTER DAY: ", String.class);
        String date = String.format("%s/%s/%s", year, month, day);

        String resultDate = weatherValidator.addWeatherDateValidation(date) ? date :
                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String longitude = weatherValidator.longitudeValidation(lon) ? lon : "";
        String latitude = weatherValidator.latitudeValidation(lat) ? lat : "";
        WeatherApi weatherApi = weatherService.addWeatherForCoordinates(API_URL_COORDINATES, longitude, latitude,
                resultDate);
        System.out.println(weatherApi != null ? weatherApi.getCity().getName() + " SUCCESSFULLY ADDED!" :
                "CANNOT FIND CITY!");
    }

    //-------------------------------------------------Display Methods--------------------------------------------------

    //Display all
    public void displayAllWeathers() {
        if (weatherService.findAllWeathers().isEmpty()) {
            System.out.println("THERE IS NO WEATHERS TO DISPLAY! ADD FIRST!");
        } else {
            System.out.println("LIST OF ALL WEATHERS: ");
            weatherService.findAllWeathers().forEach(System.out::println);
        }
    }

    //Display for id
    public void displayWeatherForGivenWeatherId() {
        if (!displayAllIds()) {
            return;
        }

        Integer weatherId = getUserChoice("ENTER WEATHER ID: ", Integer.class);

        if (weatherService.findWeatherForGivenWeatherId(weatherId) == null) {
            System.out.printf("WEATHER WITH ID = %s DOES NOT EXIST!%n", weatherId);
        } else {
            System.out.printf("WEATHER FOR ID = %s:%n", weatherId);
            System.out.println(weatherService.findWeatherForGivenWeatherId(weatherId));
        }
    }

    //Display for city
    public void displayWeatherForGivenCity() {
        if (!displayDistinctCityNames()) {
            return;
        }

        String cityName = getUserChoice("ENTER CITY NAME: ", String.class);

        if (weatherService.findWeatherForGivenCity(cityName).isEmpty()) {
            System.out.printf("THERE IS NO WEATHER FOR CITY NAMED \"%s\"!%n", cityName);
        } else {
            System.out.printf("WEATHER FOR %s:%n", cityName);
            weatherService.findWeatherForGivenCity(cityName).forEach(System.out::println);
        }
    }

    //Display for city and date
    public void displayWeatherForGivenCityAndDate() {
        if (!displayDistinctCityNames()) {
            return;
        }

        String cityName = getUserChoice("ENTER CITY NAME: ", String.class);
        String year = getUserChoice("ENTER YEAR: ", String.class);
        String month = getUserChoice("ENTER MONTH: ", String.class);
        String day = getUserChoice("ENTER DAY: ", String.class);
        String date = String.format("%s/%s/%s", year, month, day);

        if (weatherService.findWeatherForGivenCityAndDate(cityName, date).isEmpty()) {
            System.out.printf("THERE IS NO WEATHER FOR CITY NAMED \"%s\"!%n", cityName);
        } else {
            System.out.printf("WEATHER FOR %s:%n", cityName);
            weatherService.findWeatherForGivenCityAndDate(cityName, date).forEach(System.out::println);
        }

//        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date :
//                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        String resultCityName = weatherValidator.cityNameValidation(cityName) ? cityName : "";
//        weatherService.findWeatherForGivenCityAndDate(resultCityName, resultDate).forEach(System.out::println);
    }

    //Display for coordinates and date
    public void displayWeatherForGivenCoordinatesAndDate() {
        if (!displayDistinctCoordinates()) {
            return;
        }

        String lon = getUserChoice("ENTER LON: ", String.class);
        String lat = getUserChoice("ENTER LAT: ", String.class);
        String year = getUserChoice("ENTER YEAR: ", String.class);
        String month = getUserChoice("ENTER MONTH: ", String.class);
        String day = getUserChoice("ENTER DAY: ", String.class);
        String date = String.format("%s/%s/%s", year, month, day);

        if (weatherService.findWeatherForGivenCoordinatesAndDate(lon, lat, date).isEmpty()) {
            System.out.printf("THERE IS NO WEATHER FOR COORDINATES (%s, %s)!%n", lon, lat);
        } else {
            System.out.printf("WEATHER FOR %s %s %s:%n", lon, lat, date);
            weatherService.findWeatherForGivenCoordinatesAndDate(lon, lat, date).forEach(System.out::println);
        }

//        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date :
//                LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        String longitude = weatherValidator.longitudeValidation(lon) ? lon : "";
//        String latitude = weatherValidator.latitudeValidation(lon) ? lon : "";
//        System.out.printf("Weather for %s %s %s:%n", lon, lat, date);
//        weatherService.findWeatherForGivenCoordinatesAndDate(longitude, latitude, resultDate)
//                .forEach(System.out::println);
    }

    //------------------------------------------------Additional methods------------------------------------------------

    private boolean displayDistinctCityNames() {
        if (weatherService.findDistinctCityNames().isEmpty()) {
            System.out.println("NO DISTINCT CITIES FOUND!");
            return false;
        } else {
            System.out.println("AVAILABLE CITIES: ");
            System.out.println(weatherService.findDistinctCityNames());
            return true;
        }
    }

    private boolean displayDistinctCoordinates() {
        if (weatherService.findDistinctCoordinates().isEmpty()) {
            System.out.println("NO DISTINCT COORDINATES FOUND!");
            return false;
        } else {
            System.out.println("AVAILABLE COORDINATES: ");
            System.out.println(weatherService.findDistinctCoordinates());
            return true;
        }
    }

    private boolean displayAllIds() {
        if (weatherService.findAllIds().isEmpty()) {
            System.out.println("NO IDS FOUND!");
            return false;
        } else {
            System.out.println("AVAILABLE IDS: ");
            System.out.println(weatherService.findAllIds());
            return true;
        }
    }
}
