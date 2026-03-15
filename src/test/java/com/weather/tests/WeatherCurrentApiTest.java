package com.weather.tests;

import com.weather.client.WeatherApiClient;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherCurrentApiTest {
    private final WeatherApiClient client = new WeatherApiClient();

    @Test
    public void shouldRetrieveWeatherForMultipleMajorCities() {
        String[] cities = {"Sydney", "City of London", "New York City", "Tokyo"};

        for (String city: cities) {
            Response response = client.getCurrentWeather(city);

            assertEquals("Status code mismatch for city: " + city, 200, response.getStatusCode());

            int dataSize = response.jsonPath().getList("data").size();
            assertTrue("No weather data returned for city: " + city, dataSize > 0);

            String returnedCity = response.jsonPath().getString("data[0].city_name");
            assertEquals("Returned city name mismatch for: " + city, city, returnedCity);

            Double temperature = response.jsonPath().getDouble("data[0].temp");
            assertNotNull("Temperature is null for city: " + city, temperature);

            System.out.println(city + " temperature: " + temperature);
        }
    }
}
