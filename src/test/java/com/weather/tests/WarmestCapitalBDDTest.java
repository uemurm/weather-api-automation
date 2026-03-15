package com.weather.tests;

import com.weather.steps.WeatherAnalyticsSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class WarmestCapitalBDDTest {

    @Steps
    WeatherAnalyticsSteps weatherSteps;

    @Test
    public void as_a_data_analyst_i_want_to_identify_the_warmest_australian_capital() {

        weatherSteps.a_list_of_australian_capitals();
        weatherSteps.the_temperature_data_is_retrieved();
        weatherSteps.the_warmest_city_should_be_identified();
    }
}
