package repository;

import dao.WeatherDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherService {

    private WeatherRepository weatherRepository;
    private WeatherDao weatherDao;

    public LocalDate parseUnixDateToLocalDate(long unixDate) {
        return Instant.ofEpochMilli(unixDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
