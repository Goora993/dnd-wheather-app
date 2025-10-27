package com.dnd.weather.management.dto.response;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;
import lombok.Builder;

@Builder
public record CurrentWeatherResponse(WeatherType weatherType, WindType windType, WindDirection windDirection, int duration) {
}