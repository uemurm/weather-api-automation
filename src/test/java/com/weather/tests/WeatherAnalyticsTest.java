package com.weather.tests;

import com.weather.service.WeatherService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherAnalyticsTest {
    @Test
    @Tag("integration")
    void shouldIdentifyWarmestAustralianCapital() {

        List<String> capitals = List.of(
                "Sydney",
                "Melbourne",
                "Brisbane",
                "Perth",
                "Adelaide",
                "Canberra",
                "Hobart",
                "Darwin"
        );

        WeatherService service = new WeatherService();
        String warmestCity = service.findWarmestCity(capitals);

        assertNotNull(warmestCity);
        assertTrue(capitals.contains(warmestCity));
        System.out.println("Warmest city: " + warmestCity);
    }
}
