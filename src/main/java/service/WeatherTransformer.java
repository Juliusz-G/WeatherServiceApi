package service;

import model.api.ListItem;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class WeatherTransformer {

    public WeatherDto fromApiToDto(ListItem listItem) {
        WeatherDto weatherDto = new WeatherDto();
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
        weatherDto.setCityName(weatherEntity.getCityName());
        weatherDto.setLon(weatherEntity.getLon());
        weatherDto.setLat(weatherEntity.getLat());
        weatherDto.setDate(parseUnixTimestampToSqlTimestamp(weatherEntity.getDate().getTime()));
        weatherDto.setTemp(weatherEntity.getTemp());
        weatherDto.setHumidity(weatherEntity.getHumidity());
        weatherDto.setPressure(weatherEntity.getPressure());
        weatherDto.setWindDeg(weatherEntity.getWindDeg());
        weatherDto.setWindSpeed(weatherEntity.getWindSpeed());

        return weatherDto;

    }

    private Timestamp parseUnixTimestampToSqlTimestamp(long unixTimestamp) {
        return Timestamp.from(Instant.ofEpochSecond(unixTimestamp));
    }
}
