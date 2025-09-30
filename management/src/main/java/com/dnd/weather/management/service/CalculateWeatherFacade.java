package com.dnd.weather.management.service;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.management.dto.request.RollWeatherRequest;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.persistence.repository.SessionJpaRepository;
import com.dnd.weather.persistence.repository.SessionStateJpaRepository;
import jakarta.transaction.Transactional;

public class CalculateWeatherFacade {

    private final SessionJpaRepository sessionJpaRepository;
    private final SessionStateJpaRepository sessionStateJpaRepository;
    private final CalculateWeatherService calculateWeatherService;
    private final CalculateWindService calculateWindService;
    private final CalculateWindDirectionService calculateWindDirectionService;
    private final CalculateTimeService calculateTimeService;

    public CalculateWeatherFacade(SessionJpaRepository sessionJpaRepository,
                                  SessionStateJpaRepository sessionStateJpaRepository,
                                  CalculateWeatherService calculateWeatherService,
                                  CalculateWindService calculateWindService,
                                  CalculateWindDirectionService calculateWindDirectionService,
                                  CalculateTimeService calculateTimeService) {
        this.sessionJpaRepository = sessionJpaRepository;
        this.sessionStateJpaRepository = sessionStateJpaRepository;
        this.calculateWeatherService = calculateWeatherService;
        this.calculateWindService = calculateWindService;
        this.calculateWindDirectionService = calculateWindDirectionService;
        this.calculateTimeService = calculateTimeService;
    }

    @Transactional
    public CurrentWeatherResponse calculateWeather(RollWeatherRequest rollWeatherRequest) {
        Session session = sessionJpaRepository.findByUserDataId(1);
        SessionState sessionState = sessionStateJpaRepository.findBySession(session);

        sessionState = calculateWeatherService.calculateWeather(rollWeatherRequest.weatherRoll(), sessionState);
        sessionState = calculateWindService.calculateWind(rollWeatherRequest.weatherRoll(), sessionState);
        sessionState = calculateWindDirectionService.calculateWindDirection(rollWeatherRequest.weatherRoll(), sessionState);

        sessionStateJpaRepository.save(sessionState);

        int duration = calculateTimeService.calculateTime(rollWeatherRequest.timeRoll());

        System.out.println("Weather roll [" + rollWeatherRequest.weatherRoll() + "]: duration " + duration + "h "
                + ", weather: " + sessionState.getWeather()
                + ", wind: " + sessionState.getWind()
                + ", wind direction: " + sessionState.getWindDirection());

        return new CurrentWeatherResponse(
                sessionState.getWeather().toString(),
                sessionState.getWind().toString(),
                sessionState.getWindDirection().toString(),
                duration
        );
    }

}
