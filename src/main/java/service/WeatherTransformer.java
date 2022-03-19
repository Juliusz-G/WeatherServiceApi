package service;

import model.dto.WeatherDto;
import model.entity.WeatherEntity;
import model.api.WeatherApi;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class WeatherTransformer {

    public List<WeatherDto> fromApiToDto(WeatherApi weatherApi) {
        List<WeatherDto> weatherDtoList = new ArrayList<>();

        for (int i = 0; i < weatherApi.getList().size() - 1; i++) {
            WeatherDto weatherDto = new WeatherDto();
            weatherDto.setCityName(weatherApi.getCity().getName());
            weatherDto.setLon(weatherApi.getCity().getCoord().getLon());
            weatherDto.setLat(weatherApi.getCity().getCoord().getLat());
            weatherDto.setDate(parseUnixTimestampToSqlTimestamp(weatherApi.getList().get(i).getDt()));
            weatherDto.setTemp(weatherApi.getList().get(i).getMain().getTemp());
            weatherDto.setHumidity(weatherApi.getList().get(i).getMain().getHumidity());
            weatherDto.setPressure(weatherApi.getList().get(i).getMain().getPressure());
            weatherDto.setWindDeg(weatherApi.getList().get(i).getWind().getDeg());
            weatherDto.setWindSpeed(weatherApi.getList().get(i).getWind().getSpeed());
            weatherDtoList.add(weatherDto);
        }

        return weatherDtoList;
    }

    public List<WeatherEntity> fromDtoToEntity(List<WeatherDto> weatherDtoList) {
        List<WeatherEntity> weatherEntityList = new ArrayList<>();

        for (int i = 0; i < weatherDtoList.size() - 1; i++) {
            WeatherEntity weatherEntity = new WeatherEntity();
            weatherEntity.setCityName(weatherDtoList.get(i).getCityName());
            weatherEntity.setLon(weatherDtoList.get(i).getLon());
            weatherEntity.setLat(weatherDtoList.get(i).getLat());
            weatherEntity.setDate(weatherDtoList.get(i).getDate());
            weatherEntity.setTemp(weatherDtoList.get(i).getTemp());
            weatherEntity.setHumidity(weatherDtoList.get(i).getHumidity());
            weatherEntity.setPressure(weatherDtoList.get(i).getPressure());
            weatherEntity.setWindDeg(weatherDtoList.get(i).getWindDeg());
            weatherEntity.setWindSpeed(weatherDtoList.get(i).getWindSpeed());
            weatherEntityList.add(weatherEntity);
        }

        return weatherEntityList;
    }

    private Timestamp parseUnixTimestampToSqlTimestamp(long unixTimestamp) {
        return Timestamp.from(Instant.ofEpochSecond(unixTimestamp));
    }
}
