package service;

import dao.WeatherDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherService {

    private WeatherRepository weatherRepository;
    private WeatherDao weatherDao;
    private WeatherTransformer weatherTransformer;
}
