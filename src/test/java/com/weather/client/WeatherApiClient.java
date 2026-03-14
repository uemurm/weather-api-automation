package com.weather.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class WeatherApiClient {

    private static final String API_KEY;
    private static final String BASE_URL = "https://api.weatherbit.io/v2.0";

    static {
        RestAssured.baseURI = BASE_URL;

        API_KEY = System.getenv("WEATHER_API_KEY");
        if (API_KEY == null || API_KEY.isBlank()) {
            throw new IllegalStateException("WEATHER_API_KEY is not set");
        }
    }

    public Response getCurrentWeather(String city) {
        return given()
                .queryParam("city", city)
                .queryParam("key", API_KEY)
                .when()
                .get("/current")
                .then()
                .extract()
                .response();
    }
}