package service;

import java.time.LocalDate;

public class WeatherValidator {

    public boolean dateFormatValidation(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        System.out.println("INCORRECT DATE FORMAT! DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }

    public boolean dateIsNotEmptyValidation(String date) {
        if (!date.isEmpty()) {
            return true;
        }
        System.out.println("NO DATE ENTERED, DISPLAYING WEATHER FOR TOMORROW");
        return false;
    }

    public boolean dateScopeValidation(String date) {
        LocalDate localDate = LocalDate.parse(date);
        if (localDate.isAfter(LocalDate.now().plusDays(5)) || localDate.isBefore(LocalDate.now())) {
            System.out.println("WRONG DATE, DISPLAYING WEATHER FOR TOMORROW");
            return false;
        }
        return true;
    }

    public boolean dateValidation(String date) {
        if (dateIsNotEmptyValidation(date)) {
            if (dateFormatValidation(date)) {
                return dateScopeValidation(date);
            } else {
                return false;
            }
        }
        return false;
    }
}
