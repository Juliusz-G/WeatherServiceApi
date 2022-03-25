package service;

import dao.WeatherDao;
import model.dto.WeatherDto;
import model.entity.WeatherEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {
    @Mock
    private WeatherRepository weatherRepository;
    @Mock
    private WeatherDao weatherDao;
    private WeatherService weatherService;

    @BeforeEach
    void init() {
        weatherService = new WeatherService(weatherRepository, weatherDao, new WeatherTransformer(), new WeatherValidator());
    }

    @Test
    public void when_listAllWeathers_is_used_then_list_of_all_dto_objects_should_be_returned() {
        //given
        WeatherEntity weatherEntity1 = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        WeatherEntity weatherEntity2 = new WeatherEntity(2, 30, 40
                , "Krakow", Timestamp.valueOf("2022-03-24 18:00:00")
                , 20, 45, 1005, 20, 35);
        Mockito.when(weatherDao.findAllWeathers()).thenReturn(List.of(weatherEntity1, weatherEntity2));
        //when
        List<WeatherDto> weatherDtoList = weatherService.listAllWeathers();
        //then
        assertAll(
                () -> assertEquals(weatherDtoList.size(), 2),
                () -> assertEquals(weatherDtoList.get(0).getPressure(), 1001),
                () -> assertEquals(weatherDtoList.get(0).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getLat(), 40),
                () -> assertEquals(weatherDtoList.get(1).getTemp(), 20)
        );
    }

    @Test
    void when_findWeatherForGivenWeatherId_is_used_then_concrete_dto_object_should_be_returned() {
        //given
        WeatherEntity weatherEntity = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        Mockito.when(weatherDao.findById(1)).thenReturn(weatherEntity);
        //when
        WeatherDto weatherDto = weatherService.findWeatherForGivenWeatherId(1);
        //then
        assertAll(
                () -> assertEquals(weatherDto.getWeatherId(), 1),
                () -> assertEquals(weatherDto.getCityName(), "Warsaw"),
                () -> assertEquals(weatherDto.getPressure(), 1001)
        );
    }

    @Test
    void when_findWeatherForGivenCity_is_used_then_list_of_concrete_dto_objects_should_be_returned(){
        //given
        WeatherEntity weatherEntity1 = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        WeatherEntity weatherEntity2 = new WeatherEntity(2, 30, 40
                , "Warsaw", Timestamp.valueOf("2022-03-24 18:00:00")
                , 20, 45, 1005, 20, 35);
        Mockito.when(weatherDao.findByCity("Warsaw")).thenReturn(List.of(weatherEntity1, weatherEntity2));
        //when
        List<WeatherDto> weatherDtoList = weatherService.findWeatherForGivenCity("Warsaw");
        //then
        assertAll(
                () -> assertEquals(weatherDtoList.size(), 2),
                () -> assertEquals(weatherDtoList.get(0).getPressure(), 1001),
                () -> assertEquals(weatherDtoList.get(0).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getTemp(), 20)
        );
    }

    @Test
    void when_findWeatherForGivenCity_is_used_and_cityName_is_not_valid_then_list_of_all_dto_objects_should_be_returned(){
        //given
        WeatherEntity weatherEntity1 = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        WeatherEntity weatherEntity2 = new WeatherEntity(2, 30, 40
                , "Krakow", Timestamp.valueOf("2022-03-24 18:00:00")
                , 20, 45, 1005, 20, 35);
        Mockito.when(weatherDao.findAllWeathers()).thenReturn(List.of(weatherEntity1, weatherEntity2));
        //when
        List<WeatherDto> weatherDtoList = weatherService.findWeatherForGivenCity("");
        //then
        assertAll(
                () -> assertEquals(weatherDtoList.size(), 2),
                () -> assertEquals(weatherDtoList.get(0).getPressure(), 1001),
                () -> assertEquals(weatherDtoList.get(0).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getCityName(), "Krakow"),
                () -> assertEquals(weatherDtoList.get(1).getTemp(), 20)
        );
    }

    @Test
    void when_findWeatherForGivenCityAndDate_is_used_then_list_of_concrete_dto_objects_should_be_returned(){
        //given
        WeatherEntity weatherEntity1 = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        WeatherEntity weatherEntity2 = new WeatherEntity(2, 30, 40
                , "Warsaw", Timestamp.valueOf("2022-03-25 18:00:00")
                , 20, 45, 1005, 20, 35);
        Mockito.when(weatherDao.findByCityAndDate("Warsaw","2022/03/25")).thenReturn(List.of(weatherEntity1, weatherEntity2));
        //when
        List<WeatherDto> weatherDtoList = weatherService.findWeatherForGivenCityAndDate("Warsaw","2022/03/25");
        //then
        assertAll(
                () -> assertEquals(weatherDtoList.size(), 2),
                () -> assertEquals(weatherDtoList.get(0).getPressure(), 1001),
                () -> assertEquals(weatherDtoList.get(0).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getTemp(), 20)
        );
    }

    @Test
    void when_findWeatherForGivenCityAndDate_is_used_and_cityName_is_not_valid_then_list_of_concrete_dto_objects_should_be_returned(){
        //given
        WeatherEntity weatherEntity1 = new WeatherEntity(1, 20, 50
                , "Warsaw", Timestamp.valueOf("2022-03-25 15:00:00")
                , 15, 60, 1001, 15, 50);
        WeatherEntity weatherEntity2 = new WeatherEntity(2, 30, 40
                , "Krakow", Timestamp.valueOf("2022-03-25 18:00:00")
                , 20, 45, 1005, 20, 35);
        Mockito.when(weatherDao.findByDate("2022/03/25")).thenReturn(List.of(weatherEntity1, weatherEntity2));
        //when
        List<WeatherDto> weatherDtoList = weatherService.findWeatherForGivenCityAndDate("","2022/03/25");
        //then
        assertAll(
                () -> assertEquals(weatherDtoList.size(), 2),
                () -> assertEquals(weatherDtoList.get(0).getPressure(), 1001),
                () -> assertEquals(weatherDtoList.get(0).getCityName(), "Warsaw"),
                () -> assertEquals(weatherDtoList.get(1).getCityName(), "Krakow"),
                () -> assertEquals(weatherDtoList.get(1).getTemp(), 20)
        );
    }

    @Test
    void when_epochToLocalDate_is_used_then_localDate_should_be_returned(){
        //given
        //when
        LocalDate localDate=weatherService.epochToLocalDate(1648220400L);
        //then
        assertEquals(LocalDate.of(2022,3,25),localDate);
    }

    @Test
    void when_stringToLocalDate_is_used_and_date_is_valid_then_concrete_localDate_should_be_returned(){
        //given
        //when
        LocalDate localDate=weatherService.stringToLocalDate("2022/03/25");
        //then
        assertEquals(LocalDate.of(2022,3,25),localDate);
    }

    @Test
    void when_stringToLocalDate_is_used_and_date_is_not_valid_then_tomorrow_localDate_should_be_returned(){
        //given
        //when
        LocalDate localDate=weatherService.stringToLocalDate("2022/03/2");
        //then
        assertEquals(LocalDate.now().plusDays(1),localDate);
    }



}