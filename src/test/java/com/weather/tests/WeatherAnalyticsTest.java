package com.weather.tests;

import com.weather.service.WeatherService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class WeatherAnalyticsTest {
    @Test
    public void shouldIdentifyWarmestAustralianCapital() {

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
