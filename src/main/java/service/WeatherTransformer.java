package service;

import model.api.City;
import model.api.ListItem;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class WeatherTransformer {

    public WeatherDto fromApiToDto(ListItem listItem, City city) {
        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setCityName(city.getName());
        weatherDto.setLon(city.getCoord().getLon());
        weatherDto.setLat(city.getCoord().getLat());
        weatherDto.setDate(parseUnixTimestampToSqlTimestamp(listItem.getDt()));
        weatherDto.setTemp(listItem.getMain().getTemp());
        weatherDto.setHumidity(listItem.getMain().getHumidity());
        weatherDto.setPressure(listItem.getMain().getPressure());
        weatherDto.setWindDeg(listItem.getWind().getDeg());
        weatherDto.setWindSpeed(listItem.getWind().getSpeed());

        return weatherDto;
    }

    public WeatherEntity fromDtoToEntity(WeatherDto weatherDto) {

        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityName(weatherDto.getCityName());
        weatherEntity.setLon(weatherDto.getLon());
        weatherEntity.setLat(weatherDto.getLat());
        weatherEntity.setDate(weatherDto.getDate());
        weatherEntity.setTemp(weatherDto.getTemp());
        weatherEntity.setHumidity(weatherDto.getHumidity());
        weatherEntity.setPressure(weatherDto.getPressure());
        weatherEntity.setWindDeg(weatherDto.getWindDeg());
        weatherEntity.setWindSpeed(weatherDto.getWindSpeed());

        return weatherEntity;
    }

    public WeatherDto fromEntityToDto(WeatherEntity weatherEntity) {

        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setWeatherId(weatherEntity.getWeatherId());
        weatherDto.setCityName(weatherEntity.getCityName());
        weatherDto.setLon(weatherEntity.getLon());
        weatherDto.setLat(weatherEntity.getLat());
        weatherDto.setDate(weatherEntity.getDate());
        weatherDto.setTemp(weatherEntity.getTemp());
        weatherDto.setHumidity(weatherEntity.getHumidity());
        weatherDto.setPressure(weatherEntity.getPressure());
        weatherDto.setWindDeg(weatherEntity.getWindDeg());
        weatherDto.setWindSpeed(weatherEntity.getWindSpeed());

        return weatherDto;
    }

    public WeatherEntity fromApiToEntity(ListItem listItem, City city) {

        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setCityName(city.getName());
        weatherEntity.setLon(city.getCoord().getLon());
        weatherEntity.setLat(city.getCoord().getLat());
        weatherEntity.setDate(parseUnixTimestampToSqlTimestamp(listItem.getDt()));
        weatherEntity.setTemp(listItem.getMain().getTemp());
        weatherEntity.setHumidity(listItem.getMain().getHumidity());
        weatherEntity.setPressure(listItem.getMain().getPressure());
        weatherEntity.setWindDeg(listItem.getWind().getDeg());
        weatherEntity.setWindSpeed(listItem.getWind().getSpeed());

        return weatherEntity;
    }

    private Timestamp parseUnixTimestampToSqlTimestamp(long unixTimestamp) {
        return Timestamp.from(Instant.ofEpochSecond(unixTimestamp));
    }

}
