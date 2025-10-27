package com.dnd.weather.management.mapper.dto;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;

public interface CurrentWeatherDtoMapper {

    CurrentWeatherResponse toResponse(SessionBusinessObject sessionBusinessObject);

}
