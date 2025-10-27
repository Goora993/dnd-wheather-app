package com.dnd.weather.management.service;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.persistence.repository.WeatherRollRuleJpaRepository;

public class CalculateWeatherService {

    private final WeatherRollRuleJpaRepository weatherRollRuleJpaRepository;

    public CalculateWeatherService(WeatherRollRuleJpaRepository weatherRollRuleJpaRepository) {
        this.weatherRollRuleJpaRepository = weatherRollRuleJpaRepository;
    }

    public WeatherType calculateWeather(int roll, WeatherType currentWeather) {
        return weatherRollRuleJpaRepository.findResultWeather(currentWeather, roll);
    }

}
