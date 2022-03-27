package service;

import model.api.*;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherTransformerTest {
    private final WeatherTransformer weatherTransformer = new WeatherTransformer();

    @Test
    void when_fromEntityToDto_is_used_then_dto_object_should_be_returned() {
        //given
        WeatherEntity weatherEntity = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        //when
        WeatherDto weatherDto = weatherTransformer.fromEntityToDto(weatherEntity);
        //then
        assertAll(
                () -> assertEquals(weatherDto.getWeatherId(), weatherEntity.getWeatherId()),
                () -> assertEquals(weatherDto.getCityName(), weatherEntity.getCityName()),
                () -> assertEquals(weatherDto.getPressure(), weatherEntity.getPressure())
        );
    }

    @Test
    void when_fromApiToEntity_is_used_then_entity_object_should_be_returned() {
        //given
        Main main = new Main(25, 60, 1001);
        Wind wind = new Wind(15, 45);
        ListItem listItem = new ListItem(1648220400, main, wind);
        City city = new City(new Coord(20, 50), "Krakow");
        //when
        WeatherEntity weatherEntity = weatherTransformer.fromApiToEntity(listItem, city);
        //then
        assertAll(
                () -> assertEquals(weatherEntity.getHumidity(), listItem.getMain().getHumidity()),
                () -> assertEquals(weatherEntity.getCityName(), city.getName()),
                () -> assertEquals(weatherEntity.getWindSpeed(), listItem.getWind().getSpeed())
        );
    }
}