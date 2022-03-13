package repository;

import jsonModel.WeatherDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRepository {

    private final Gson gson;
    private final Logger logger = LogManager.getLogger(WeatherRepository.class);

    public WeatherRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public WeatherDto getWeatherData(String url) {
        WeatherDto weather = null;
        try {
            URL urlAddress = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlAddress.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) httpURLConnection.getContent()));
            weather = gson.fromJson(bufferedReader, WeatherDto.class);
            httpURLConnection.disconnect();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return weather;
    }
}
