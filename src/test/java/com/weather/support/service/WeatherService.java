package com.weather.support.service;

import com.weather.support.client.WeatherApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.truncate;

public class WeatherService {

    private final WeatherApiClient client = new WeatherApiClient();

    public String findWarmestCity(List<String> cities) {

        List<String> failures = new ArrayList<>();

        String warmestCity = null;
        double highestTemperature = Double.NEGATIVE_INFINITY;

        for (String city : cities) {
            Response response;
            try {
                response = client.getCurrentWeather(city);
            } catch (Exception e) {
                failures.add(city + " -> request failed: " + e.getMessage());
                continue;
            }

            if (response.getStatusCode() != 200) {
                failures.add(city + " -> unexpected status: " + response.getStatusCode()
                        + ", body: " + truncate(response.asString(), 300));
                continue;
            }

            JsonPath jsonPath = response.jsonPath();
            Double temperature = jsonPath.getDouble("data[0].temp");

            if (temperature == null) {
                failures.add(city + " -> temp is null, body: " + truncate(response.asString(), 300));
                continue;
            }

            if (temperature > highestTemperature) {
                highestTemperature = temperature;
                warmestCity = city;
            }
        }

        if (!failures.isEmpty()) {
            throw new AssertionError("Weather data retrieval had failures:\n- " + failures);
        }

        if (warmestCity == null) {
            throw new RuntimeException("No valid temperature data found.");
        }

        return warmestCity;
    }
}
