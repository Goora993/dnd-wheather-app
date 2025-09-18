package com.dnd.weather.management.controller;

import com.dnd.weather.management.dto.request.RollWeatherRequest;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.management.service.CalculateWeatherFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final CalculateWeatherFacade calculateWeatherFacade;

    public WeatherController(CalculateWeatherFacade calculateWeatherFacade) {
        this.calculateWeatherFacade = calculateWeatherFacade;
    }

    @PostMapping("/roll")
    public ResponseEntity<CurrentWeatherResponse> rollWeather(@Validated @RequestBody RollWeatherRequest request) {
        CurrentWeatherResponse currentWeatherResponse = calculateWeatherFacade.calculateWeather(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currentWeatherResponse);
    }

}
