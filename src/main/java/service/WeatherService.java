package service;

import dao.WeatherDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.api.WeatherApi;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        weatherApi.getList().stream().map(weather -> weatherTransformer.fromApiToDto(weather))
                .peek(weatherDto -> {
                    weatherDto.setCityName(weatherApi.getCity().getName());
                    weatherDto.setLon(weatherApi.getCity().getCoord().getLon());
                    weatherDto.setLat(weatherApi.getCity().getCoord().getLat());
                }).map(weatherDto -> weatherTransformer.fromDtoToEntity(weatherDto))
                .forEach(weatherEntity -> weatherDao.saveOrUpdate(weatherEntity));

        return weatherApi;
    }

    public void addWeatherForGivenCity(String apiUrl, String cityName) {

        WeatherApi weatherApi = weatherRepository
                .jsonDeserialization(String
                        .format(apiUrl, cityName), WeatherApi.class
                );
        weatherApi.getList().stream().map(weather -> weatherTransformer.fromApiToDto(weather))
                .peek(weatherDto -> {
                    weatherDto.setCityName(weatherApi.getCity().getName());
                    weatherDto.setLon(weatherApi.getCity().getCoord().getLon());
                    weatherDto.setLat(weatherApi.getCity().getCoord().getLat());
                }).map(weatherDto -> weatherTransformer.fromDtoToEntity(weatherDto))
                .forEach(weatherEntity -> weatherDao.saveOrUpdate(weatherEntity));
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

    public List<WeatherDto> listAllWeathers() {
        return weatherDao.findAllWeathers().stream().map(weatherEntity -> weatherTransformer.fromEntityToDto(weatherEntity)).collect(Collectors.toList());
    }

    public WeatherDto findWeatherForGivenWeatherId(Integer weatherId) {
        return weatherTransformer.fromEntityToDto(weatherDao.findById(weatherId));
    }

    public List<WeatherDto> findWeatherForGivenCity(String cityname) {
        return weatherDao.findByCity(cityname).stream().map(weatherEntity -> weatherTransformer.fromEntityToDto(weatherEntity)).collect(Collectors.toList());
    }

    public List<WeatherDto> findWeatherForGivenCityAndDate(String cityName, String date) {
        return weatherDao.findByCityAndDate(cityName, date)
                .stream().map(weatherEntity -> weatherTransformer.fromEntityToDto(weatherEntity)).collect(Collectors.toList());
    }

    public List<String> displayDistinctCityNames() {
        return weatherDao.findAllWeathers()
                .stream()
                .filter(distinctByKey(WeatherEntity::getCityName))
                .map(WeatherEntity::getCityName)
                .collect(Collectors.toList());
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
