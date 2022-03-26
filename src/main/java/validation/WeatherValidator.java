package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherValidator {

    public boolean longitudeScopeValidation(String longitude) {
        if (Double.parseDouble(longitude) >= -180 && Double.parseDouble(longitude) <= 180) {
            return true;
        }
        System.out.println("LONGITUDE OUT OF SCOPE!");
        return false;
    }

    public boolean latitudeScopeValidation(String latitude) {
        if (Double.parseDouble(latitude) >= -90 && Double.parseDouble(latitude) <= 90) {
            return true;
        }
        System.out.println("LATITUDE OUT OF SCOPE!");
        return false;
    }

    public boolean coordinateFormatValidation(String input) {
        if (!input.matches("^[0-9]+\\.?[0-9]*$")) {
            System.out.println("INCORRECT FORMAT!");
            return false;
        }
        return true;
    }

    public boolean isFieldNotEmpty(String input) {
        return !input.isEmpty();
    }

    public boolean longitudeValidation(String input) {
        if (isFieldNotEmpty(input)) {
            if (coordinateFormatValidation(input)) {
                return longitudeScopeValidation(input);
            }
            return false;
        }
        System.out.println("LONGITUDE CANNOT BE EMPTY!");
        return false;
    }

    public boolean latitudeValidation(String input) {
        if (isFieldNotEmpty(input)) {
            if (coordinateFormatValidation(input)) {
                return latitudeScopeValidation(input);
            }
            return false;
        }
        System.out.println("LATITUDE CANNOT BE EMPTY!");
        return false;
    }

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
        if (localDate.isAfter(LocalDate.now().plusDays(5))) {
            System.out.println("DATE OUT OF RANGE, DISPLAYING WEATHER FOR TOMORROW");
            return false;
        }
        return true;
    }

    public boolean addWeatherDateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            if (dateFormatValidation(date)) {
                return addWeatherDateScopeValidation(date);
            }
            System.out.println("INCORRECT DATE FORMAT! IF FOUND-ADDING WEATHER FOR TOMORROW");
            return false;
        }
        System.out.println("NO DATE ENTERED, IF FOUND-ADDING WEATHER FOR TOMORROW");
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
