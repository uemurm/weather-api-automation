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
    public void asADataAnalystIWantToIdentifyTheWarmestAustralianCapital() {

        weatherSteps.aListOfAustralianCapitals();
        weatherSteps.theTemperatureDataIsRetrieved();
        weatherSteps.theWarmestCityShouldBeIdentified();
    }
}
