package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "weather")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_id")
    private int weatherId;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "temp_max")
    private double tempMax;

    @Column(name = "temp_min")
    private double tempMin;

    @Column(name = "temp")
    private double temp;

    @Column(name = "humidity")
    private int humidity;

    @Column(name = "pressure")
    private int pressure;
}
