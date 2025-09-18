package com.dnd.weather.management.service;

import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.dao.WeatherRollRuleDao;

public class CalculateWeatherService {

    private final WeatherRollRuleDao weatherRollRuleDao;

    public CalculateWeatherService(WeatherRollRuleDao weatherRollRuleDao) {
        this.weatherRollRuleDao = weatherRollRuleDao;
    }

    public SessionState calculateWeather(int roll, SessionState sessionState) {
        WeatherType currentWeather = sessionState.getWeather();
        WeatherType resultWeather = weatherRollRuleDao.findResultWeather(currentWeather, roll);

        sessionState.setWeather(resultWeather);

        return sessionState;
    }

}
