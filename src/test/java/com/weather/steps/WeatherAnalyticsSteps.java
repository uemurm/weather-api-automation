package com.weather.steps;

import com.weather.service.WeatherService;
import net.thucydides.core.annotations.Step;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherAnalyticsSteps {

    private final WeatherService weatherService = new WeatherService();

    private List<String> capitals;
    private String warmestCity;

    @Step("Given a list of Australian capital cities")
    public void a_list_of_australian_capitals() {
        capitals = List.of(
                "Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide", "Canberra", "Hobart", "Darwin"
        );
    }

    @Step("When temperature data is retrieved and analysed")
    public void the_temperature_data_is_retrieved() {
        warmestCity = weatherService.findWarmestCity(capitals);
    }

    @Step("Then the warmest city should be identified")
    public void the_warmest_city_should_be_identified() {
        assertThat(warmestCity)
                .isNotNull()
                .isNotBlank();
    }
}
