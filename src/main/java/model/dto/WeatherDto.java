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
    private Timestamp date;
    private double temp;
    private int humidity;
    private int pressure;
    private int windDeg;
    private double windSpeed;
}
