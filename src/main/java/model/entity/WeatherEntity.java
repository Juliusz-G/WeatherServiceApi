package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "weather_entity")
public class WeatherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private int weatherId;

    @Column(name = "lon")
    private double lon;

    @Column(name = "lat")
    private double lat;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "date")
    private Timestamp date;

    @Column(name = "temp")
    private double temp;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "pressure")
    private int pressure;

    @Column(name = "wind_deg")
    private int windDeg;

    @Column(name = "wind_speed")
    private double windSpeed;
}
