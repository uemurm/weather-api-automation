package com.weather.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherApiTest {
    private static final String API_KEY = System.getenv("WEATHER_API_KEY");
    private static final String BASE_URL = "https://api.weatherbit.io/v2.0";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;

        if (API_KEY == null) {
            throw new RuntimeException("WEATHER_API_KEY environment variable is not set.");
        }
    }

    @Test
    public void shouldRetrieveWeatherForMultipleMajorCities() {
        String[] cities = {"Sydney", "City of London", "New York City", "Tokyo"};

        for (String city: cities) {
            Response response = RestAssured
                    .given()
                    .queryParam("city", city)
                    .queryParam("key", API_KEY)
                    .when()
                    .get("/current");

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