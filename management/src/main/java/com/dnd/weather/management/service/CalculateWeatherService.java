package com.dnd.weather.management.service;

import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.persistence.repository.WeatherRollRuleJpaRepository;

public class CalculateWeatherService {

    private final WeatherRollRuleJpaRepository weatherRollRuleJpaRepository;

    public CalculateWeatherService(WeatherRollRuleJpaRepository weatherRollRuleJpaRepository) {
        this.weatherRollRuleJpaRepository = weatherRollRuleJpaRepository;
    }

    public SessionState calculateWeather(int roll, SessionState sessionState) {
        WeatherType currentWeather = sessionState.getWeather();
        WeatherType resultWeather = weatherRollRuleJpaRepository.findResultWeather(currentWeather, roll);

        sessionState.setWeather(resultWeather);

        return sessionState;
    }

}
