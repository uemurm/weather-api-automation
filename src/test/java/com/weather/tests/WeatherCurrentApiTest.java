package com.weather.tests;

import com.weather.client.WeatherApiClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherCurrentApiTest {
    private final WeatherApiClient client = new WeatherApiClient();

    @Test
    public void shouldRetrieveWeatherForMultipleMajorCities() {
        String[] cities = {"Sydney", "City of London", "New York City", "Tokyo"};

        for (String city: cities) {
            Response response = client.getCurrentWeather(city);

            assertEquals(200, response.getStatusCode(),
                    "Status code mismatch for city: " + city);

            int dataSize = response.jsonPath().getList("data").size();
            assertTrue(dataSize > 0,
                    "No weather data returned for city: " + city);

            String returnedCity = response.jsonPath().getString("data[0].city_name");
            assertEquals(city, returnedCity,
                    "Returned city name mismatch for: " + city);

            Double temperature = response.jsonPath().getDouble("data[0].temp");
            assertNotNull(temperature,
                    "Temperature is null for city: " + city);

            System.out.println(city + " temperature: " + temperature);
        }
    }
}
