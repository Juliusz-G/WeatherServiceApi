package service;

public class WeatherValidator {

    public boolean cityNameValidation(String cityName) {
        if (!cityName.isEmpty()) {
            return true;
        }
        System.out.println("NO CITY ENTERED, DISPLAYING WEATHER FOR ALL LOCATIONS");
        return false;
    }

    public boolean dateFormatValidation(String date) {
        if (date.matches("\\d{4}/\\d{2}/\\d{2}")) {
            return true;
        }
        System.out.println("INCORRECT DATE FORMAT! IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }

    public boolean dateIsNotEmptyValidation(String date) {
        if (!date.isEmpty()) {
            return true;
        }
        System.out.println("NO DATE ENTERED, IF AVAILABLE-DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }

    public boolean dateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            return dateFormatValidation(date);
        }
        return false;
    }
}
