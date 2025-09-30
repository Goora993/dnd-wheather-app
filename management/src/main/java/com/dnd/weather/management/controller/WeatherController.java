package com.dnd.weather.management.controller;

import com.dnd.weather.management.dto.request.CreateNewSessionRequest;
import com.dnd.weather.management.dto.request.RollWeatherRequest;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.management.dto.response.SessionDataResponse;
import com.dnd.weather.management.service.CalculateWeatherFacade;
import com.dnd.weather.management.service.SessionDataService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final CalculateWeatherFacade calculateWeatherFacade;
    private final SessionDataService sessionDataService;

    public WeatherController(CalculateWeatherFacade calculateWeatherFacade, SessionDataService sessionDataService) {
        this.calculateWeatherFacade = calculateWeatherFacade;
        this.sessionDataService = sessionDataService;
    }

    @PostMapping("/session")
    public ResponseEntity<SessionDataResponse> createNewSession(@Valid @RequestBody CreateNewSessionRequest request) {
        SessionDataResponse sessionDataResponse = sessionDataService.createNewSessionData(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sessionDataResponse);
    }

    @PostMapping("/roll")
    public ResponseEntity<CurrentWeatherResponse> rollWeather(@Valid @RequestBody RollWeatherRequest request) {
        CurrentWeatherResponse currentWeatherResponse = calculateWeatherFacade.calculateWeather(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(currentWeatherResponse);
    }

}
