package com.dnd.weather.management.dto.response;

public record CurrentWeatherResponse(String weatherType, String windType, String windDirection, int duration) {
}