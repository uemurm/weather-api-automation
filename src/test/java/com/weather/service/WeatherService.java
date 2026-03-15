package com.weather.service;

import com.weather.client.WeatherApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.List;

public class WeatherService {

    private final WeatherApiClient client = new WeatherApiClient();

    public String findWarmestCity(List<String> cities) {

        String warmestCity = null;
        double highestTemperature = Double.NEGATIVE_INFINITY;

        for (String city : cities) {

            Response response = client.getCurrentWeather(city);

            // Prioritise comparison over API stability.
            if (response.getStatusCode() != 200) {
                continue;
            }

            JsonPath jsonPath = response.jsonPath();
            Double temperature = jsonPath.getDouble("data[0].temp");

            if (temperature == null) {
                continue;
            }

            if (temperature > highestTemperature) {
                highestTemperature = temperature;
                warmestCity = city;
            }
        }

        if (warmestCity == null) {
            throw new RuntimeException("No valid temperature data found.");
        }

        return warmestCity;
    }
}
