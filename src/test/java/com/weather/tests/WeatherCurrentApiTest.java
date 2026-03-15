package com.weather.tests;

import com.weather.support.client.WeatherApiClient;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherCurrentApiTest {
    private final WeatherApiClient client = new WeatherApiClient();

    @Test
    public void shouldRetrieveWeatherForMultipleMajorCities() {
        List<String> cities = List.of("Sydney", "London", "New York", "Tokyo");

        for (String city: cities) {
            Response response = client.getCurrentWeather(city);

            // Simple comparison of city_name may lead to flaky tests so use robust assertions only.
            assertThat(response.getStatusCode()).isEqualTo(200);

            JsonPath jpath = response.jsonPath();

            int dataSize = jpath.getList("data").size();
            assertThat(dataSize).isGreaterThan(0);

            // Retrieved data may be missing temperature so use Double to make room for Null.
            Double temperature = jpath.getDouble("data[0].temp");
            assertThat(temperature).isNotNull();

            Double lat = jpath.getDouble("data[0].lat");
            Double lon = jpath.getDouble("data[0].lon");
            assertThat(lat).isBetween( -90.0,  90.0);
            assertThat(lon).isBetween(-180.0, 180.0);
        }
    }
}
