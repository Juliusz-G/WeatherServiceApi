package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherValidator {

    public boolean longitudeValidation(String longitude) {
        return Double.parseDouble(longitude) >= -180 && Double.parseDouble(longitude) <= 180;
    }

    public boolean latitudeValidation(String latitude) {
        return Double.parseDouble(latitude) >= -90 && Double.parseDouble(latitude) <= 90;
    }

    public boolean coordinateFormatValidation(String input) {
        return input.matches("^[0-9]+\\.?[0-9]*$");
    }

    public boolean isFieldNotEmpty(String input) {
        return input.isEmpty();
    }

//    public boolean coordinatesValidation(String longitude, String latitude) {
//        return isFieldNotEmpty(longitude) || isFieldNotEmpty(latitude);
//    }

    public boolean cityNameValidation(String cityName) {
        return !cityName.isEmpty();
    }

    public boolean dateFormatValidation(String date) {
        return date.matches("\\d{4}/\\d{2}/\\d{2}");
    }

    public boolean dateIsNotEmptyValidation(String date) {
        return !date.replaceAll("/", "").isEmpty();
    }

    public boolean addWeatherDateScopeValidation(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (localDate.isAfter(LocalDate.now().plusDays(5)) || localDate.isBefore(LocalDate.now())) {
            System.out.println("DATE OUT OF RANGE, ADDING WEATHER FOR TOMORROW");
            return false;
        }
        return true;
    }

    public boolean displayWeatherDateScopeValidation(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (localDate.isAfter(LocalDate.now().plusDays(5))){
            System.out.println("DATE OUT OF RANGE, ADDING WEATHER FOR TOMORROW");
            return false;
        }
        return true;
    }

    public boolean addWeatherDateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            if (dateFormatValidation(date)) {
                return addWeatherDateScopeValidation(date);
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
                return displayWeatherDateScopeValidation(date);
            }
            System.out.println("INCORRECT DATE FORMAT! IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
            return false;
        }
        System.out.println("NO DATE ENTERED, IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }


}
