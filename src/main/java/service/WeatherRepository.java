package service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRepository {

    private final Gson gson;
    private final Logger logger = LogManager.getLogger(WeatherRepository.class);

    public WeatherRepository() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T jsonDeserialization(String url, Class<T> tClass) {
        T weatherApi = null;
        try {
            URL urlAddress = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlAddress.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((InputStream) httpURLConnection.getContent()));
            weatherApi = gson.fromJson(bufferedReader, tClass);
            httpURLConnection.disconnect();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return weatherApi;
    }
}
