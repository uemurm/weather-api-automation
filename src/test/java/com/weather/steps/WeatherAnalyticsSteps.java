package com.weather.steps;

import com.weather.support.service.WeatherService;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherAnalyticsSteps {

    private final WeatherService weatherService = new WeatherService();

    private List<String> capitals;
    private String warmestCity;

    @Step("Given a list of Australian capital cities")
    public void aListOfAustralianCapitals() {
        capitals = List.of(
                "Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide", "Canberra", "Hobart", "Darwin"
        );
    }

    @Step("When temperature data is retrieved and analysed")
    public void theTemperatureDataIsRetrieved() {
        warmestCity = weatherService.findWarmestCity(capitals);
    }

    @Step("Then the warmest city should be identified")
    public void theWarmestCityShouldBeIdentified() {
        assertThat(warmestCity)
                .isNotNull()
                .isNotBlank()
                .isIn(capitals);
    }
}
