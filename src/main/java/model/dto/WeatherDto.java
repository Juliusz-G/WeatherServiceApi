package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherDto {

    //Data transfer Object - DTO,

    private int weatherId;
    private String cityName;
    private double lon;
    private double lat;
    private Timestamp date;
    private double temp;
    private int humidity;
    private int pressure;
    private int windDeg;
    private double windSpeed;

    @Override
    public String toString() {
        return
                "ID: " + weatherId + ", " + "CITY NAME: " + cityName + ", " + "COORDINATES: " + "(" + "LON: " + lon + ", " + "LAT: " + lat + ")" + ", " + " TEMPERATURE: " + temp + "°C," +
                        " PRESSURE: " + pressure + "hPa," +
                        " HUMIDITY: " + humidity + "%" + ", " + " WIND SPEED: " + windSpeed + "m/s, " +
                        " WIND DEGREE: " + windDeg + "°" + ", " + "DATE: " + date;
    }
}
