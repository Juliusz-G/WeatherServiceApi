package service;

import dao.WeatherDao;
import lombok.Data;
import model.api.WeatherApi;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@Data
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final WeatherDao weatherDao;
    private final WeatherTransformer weatherTransformer;
    private final WeatherValidator weatherValidator;

    public WeatherService(WeatherRepository weatherRepository, WeatherDao weatherDao, WeatherTransformer weatherTransformer, WeatherValidator weatherValidator) {
        this.weatherRepository = weatherRepository;
        this.weatherDao = weatherDao;
        this.weatherTransformer = weatherTransformer;
        this.weatherValidator = weatherValidator;
    }

    public WeatherApi addWeatherForCoordinates(String apiUrl, String lon, String lat, String date) {
        LocalDate localDate = stringToLocalDate(date);
        WeatherApi weatherApi = weatherRepository
                .jsonDeserialization(String
                        .format(apiUrl, lon, lat), WeatherApi.class
                );

        weatherApi.getList().stream()
                .filter(weather -> epochToLocalDate(weather.getDt()).equals(localDate))
                .map(weatherTransformer::fromApiToDto)
                .peek(weatherDto -> {
                    weatherDto.setCityName(weatherApi.getCity().getName());
                    weatherDto.setLon(weatherApi.getCity().getCoord().getLon());
                    weatherDto.setLat(weatherApi.getCity().getCoord().getLat());
                })
                .map(weatherTransformer::fromDtoToEntity)
                .forEach(weatherDao::saveOrUpdate);

        return weatherApi;
    }

    public WeatherApi addWeatherForGivenCity(String apiUrl, String cityName, String date) {
        LocalDate localDate = stringToLocalDate(date);
        WeatherApi weatherApi = weatherRepository
                .jsonDeserialization(String
                        .format(apiUrl, cityName), WeatherApi.class
                );
        weatherApi.getList().stream()
                .filter(weather -> epochToLocalDate(weather.getDt()).equals(localDate))
                .map(weatherTransformer::fromApiToDto)
                .peek(weatherDto -> {
                    weatherDto.setCityName(weatherApi.getCity().getName());
                    weatherDto.setLon(weatherApi.getCity().getCoord().getLon());
                    weatherDto.setLat(weatherApi.getCity().getCoord().getLat());
                })
                .map(weatherTransformer::fromDtoToEntity)
                .forEach(weatherDao::saveOrUpdate);
        return weatherApi;
    }

    public void updateWeatherForGivenCity(String cityName) {
        weatherDao.findByCity(cityName)
                .forEach(weatherDao::update);
    }

    public void deleteWeatherForGivenCity(String cityName) {
        weatherDao.findByCity(cityName)
                .forEach(weatherDao::delete
                );
    }

    public List<WeatherDto> listAllWeathers() {
        return weatherDao.findAllWeathers().stream()
                .map(weatherTransformer::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public WeatherDto findWeatherForGivenWeatherId(Integer weatherId) {
        return weatherTransformer.fromEntityToDto(weatherDao.findById(weatherId));
    }

    public List<WeatherDto> findWeatherForGivenCity(String cityName) {
        if (weatherValidator.cityNameValidation(cityName)) {
            return weatherDao.findByCity(cityName).stream()
                    .map(weatherTransformer::fromEntityToDto)
                    .collect(Collectors.toList());
        }
        return listAllWeathers();
    }

    public List<WeatherDto> findWeatherForGivenCityAndDate(String cityName, String date) {
        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date : LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        if (weatherValidator.cityNameValidation(cityName)) {
            return weatherDao.findByCityAndDate(cityName, resultDate).stream()
                    .map(weatherTransformer::fromEntityToDto)
                    .collect(Collectors.toList());
        }
        return findAllByDate(date);

    }

    public List<WeatherDto> findByCoordinatesAndDate(String lon, String lat, String date) {
        String resultDate = weatherValidator.displayWeatherDateValidation(date) ? date : LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return weatherDao.findByCoordinatesAndDate(lon, lat, resultDate).stream()
                .map(weatherTransformer::fromEntityToDto)
                .collect(Collectors.toList());
    }

    public List<WeatherDto> findAllByDate(String date) {
        return weatherDao.findByDate(date).stream().map(weatherTransformer::fromEntityToDto).collect(Collectors.toList());
    }

    public LocalDate epochToLocalDate(Long epochDate) {
        return Instant.ofEpochSecond(epochDate).atZone(ZoneId.systemDefault()).toLocalDate();
    }


    public LocalDate stringToLocalDate(String date) {
        String resultDate = weatherValidator.addWeatherDateValidation(date) ? date : LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        return LocalDate.parse(resultDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public List<String> displayDistinctCityNames() {
        return weatherDao.findAllWeathers().stream()
                .filter(distinctByKey(WeatherEntity::getCityName))
                .map(WeatherEntity::getCityName)
                .collect(Collectors.toList());
    }


    private <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


}
