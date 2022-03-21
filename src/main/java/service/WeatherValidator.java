package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherValidator {

    public boolean cityNameValidation(String cityName) {
        return !cityName.isEmpty();
    }

    public boolean dateFormatValidation(String date) {
        return date.matches("\\d{4}/\\d{2}/\\d{2}");
    }

    public boolean dateIsNotEmptyValidation(String date) {
        return !date.replaceAll("/", "").isEmpty();
    }

    public boolean dateScopeValidation(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (localDate.isAfter(LocalDate.now().plusDays(5)) || localDate.isBefore(LocalDate.now())) {
            System.out.println("DATE OUT OF RANGE, ADDING WEATHER FOR TOMORROW");
            return false;
        }
        return true;
    }

    public boolean addWeatherDateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            if (dateFormatValidation(date)) {
                return dateScopeValidation(date);
            }
            System.out.println("INCORRECT DATE FORMAT! ADDING WEATHER FOR TOMORROW");
            return false;
        }
        System.out.println("NO DATE ENTERED, ADDING WEATHER FOR TOMORROW");
        return false;
    }

    public boolean displayWeatherDateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            if (dateFormatValidation(date)) {
                return true;
            }
            System.out.println("INCORRECT DATE FORMAT! IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
            return false;
        }
        System.out.println("NO DATE ENTERED, IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }


}
