package com.dnd.weather.management.mapper.dto.impl;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.management.mapper.dto.CurrentWeatherDtoMapper;

public class CurrentWeatherDtoMapperImpl implements CurrentWeatherDtoMapper {

    @Override
    public CurrentWeatherResponse toResponse(SessionBusinessObject sessionBusinessObject) {
        return CurrentWeatherResponse.builder()
                .weatherType(sessionBusinessObject.getCurrentWeather())
                .windType(sessionBusinessObject.getCurrentWind())
                .windDirection(sessionBusinessObject.getCurrentWindDirection())
                .duration(sessionBusinessObject.getCurrentDuration())
                .build();
    }

}
