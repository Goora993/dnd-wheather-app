package com.dnd.weather.service;

import com.dnd.weather.model.entity.SessionState;
import com.dnd.weather.model.enumeration.WeatherType;
import com.dnd.weather.repository.WeatherRollRuleRepository;

public class CalculateWeatherService {

    private final WeatherRollRuleRepository weatherRollRuleRepository;

    public CalculateWeatherService(WeatherRollRuleRepository weatherRollRuleRepository) {
        this.weatherRollRuleRepository = weatherRollRuleRepository;
    }

    public SessionState calculateWeather(int roll, SessionState sessionState) {
        WeatherType currentWeather = sessionState.getWeather();
        WeatherType resultWeather = weatherRollRuleRepository.findResultWeather(currentWeather, roll);

        sessionState.setWeather(resultWeather);

        return sessionState;
    }

}
