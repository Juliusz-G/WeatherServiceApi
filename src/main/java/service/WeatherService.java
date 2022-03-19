package service;

import dao.WeatherDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.api.WeatherApi;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WeatherService {

    private WeatherRepository weatherRepository;
    private WeatherDao weatherDao;
    private WeatherTransformer weatherTransformer;

    public WeatherApi addWeatherForCoordinates(String apiUrl, String lon, String lat) {

        WeatherApi weatherApi = weatherRepository
                .jsonDeserialization(String
                        .format(apiUrl, lon, lat), WeatherApi.class
                );
        weatherTransformer
                .fromDtoToEntity(weatherTransformer
                        .fromApiToDto(weatherApi)
                )
                .forEach(w -> weatherDao
                        .saveOrUpdate(w) //if exists than update
                );
        return weatherApi;
    }

    public void addWeatherForGivenCity(String apiUrl, String cityName) {

        WeatherApi weatherApi = weatherRepository
                .jsonDeserialization(String
                        .format(apiUrl, cityName), WeatherApi.class
                );

        weatherTransformer.fromDtoToEntity(weatherTransformer
                .fromApiToDto(weatherApi)
        )
                .forEach(w -> weatherDao
                        .saveOrUpdate(w) //if exists than update
                );
    }

    public void updateWeatherForGivenCity(String cityName) {
        weatherDao.findByCity(cityName)
                .forEach(w -> weatherDao.update(w));

    }

    public void deleteWeatherForGivenCity(String cityName) {
        weatherDao
                .findByCity(cityName)
                .forEach(w -> weatherDao.delete(w)
                );

    }


}
