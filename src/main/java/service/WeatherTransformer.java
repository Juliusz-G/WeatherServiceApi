package service;

import model.api.City;
import model.api.ListItem;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class WeatherTransformer {

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
