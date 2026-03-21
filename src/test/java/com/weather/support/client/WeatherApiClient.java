package com.weather.support.client;

import com.weather.support.config.WeatherConfig;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class WeatherApiClient {

    public Response getCurrentWeather(String city) {
        return given()
                .baseUri(WeatherConfig.BASE_URL)
                .queryParam("city", city)
                .queryParam("key", WeatherConfig.apiKey())
                .when()
                .get("/current")
                .then()
                .extract()
                .response();
    }
}
