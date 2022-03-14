package entity;

import jsonModel.WeatherDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private long date;

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

    public Weather(WeatherDto weatherDto) {
        this.cityName = weatherDto.getName();
        this.date = weatherDto.getDt();
        this.tempMax = weatherDto.getMain().getTempMax();
        this.tempMin = weatherDto.getMain().getTempMin();
        this.temp = weatherDto.getMain().getTemp();
        this.humidity = weatherDto.getMain().getHumidity();
        this.pressure = weatherDto.getMain().getPressure();
    }
}
