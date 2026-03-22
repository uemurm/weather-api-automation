package com.weather.support.client;

import com.weather.support.config.WeatherConfig;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class WeatherApiClient {

    public Response getCurrentWeather(String city) {
        return getCurrentWeather(Map.of("city", city));
    }

    public Response getCurrentWeather(double lat, double lon) {
        return getCurrentWeather(Map.of(
                "lat", String.valueOf(lat),
                "lon", String.valueOf(lon)
        ));
    }

    private Response getCurrentWeather(Map<String, ?> queryParams) {
        return given()
                .baseUri(WeatherConfig.BASE_URL)
                .queryParam("key", WeatherConfig.apiKey())
                .queryParams(queryParams)
            .when()
                .get("/current")
            .then()
                .extract()
                .response();
    }
}
