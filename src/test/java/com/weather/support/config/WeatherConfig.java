package com.weather.support.config;

public class WeatherConfig {
    public static final String BASE_URL = "https://api.weatherbit.io/v2.0";

    public static String apiKey() {
        String key = System.getenv("WEATHER_API_KEY");
        if (key == null || key.isBlank()) {
            throw new IllegalStateException("WEATHER_API_KEY is not set");
        }
        return key;
    }
}
