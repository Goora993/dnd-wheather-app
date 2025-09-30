package com.dnd.weather.management.dto.request;

import com.dnd.weather.domain.enumeration.WeatherType;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.domain.enumeration.WindType;

public record CreateNewSessionRequest(String sessionName,
                                      WeatherType weather,
                                      WindType wind,
                                      WindDirection windDirection,
                                      int hour,
                                      int minute) {
}
