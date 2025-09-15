package com.dnd.weather.service;

import com.dnd.weather.model.entity.Session;
import com.dnd.weather.model.entity.SessionState;
import com.dnd.weather.repository.SessionRepository;
import com.dnd.weather.repository.SessionStateRepository;
import jakarta.transaction.Transactional;

public class CalculateWeatherFacade {

    private final SessionRepository sessionRepository;
    private final SessionStateRepository sessionStateRepository;
    private final CalculateWeatherService calculateWeatherService;
    private final CalculateWindService calculateWindService;
    private final CalculateWindDirectionService calculateWindDirectionService;
    private final CalculateTimeService calculateTimeService;

    public CalculateWeatherFacade(SessionRepository sessionRepository,
                                  SessionStateRepository sessionStateRepository,
                                  CalculateWeatherService calculateWeatherService,
                                  CalculateWindService calculateWindService,
                                  CalculateWindDirectionService calculateWindDirectionService,
                                  CalculateTimeService calculateTimeService) {
        this.sessionRepository = sessionRepository;
        this.sessionStateRepository = sessionStateRepository;
        this.calculateWeatherService = calculateWeatherService;
        this.calculateWindService = calculateWindService;
        this.calculateWindDirectionService = calculateWindDirectionService;
        this.calculateTimeService = calculateTimeService;
    }

    @Transactional
    public void calculateWeather(int weatherRoll, int timeRoll, long userId) {
        Session session = sessionRepository.findByUserDataId(userId);
        SessionState sessionState = sessionStateRepository.findBySession(session);

        sessionState = calculateWeatherService.calculateWeather(weatherRoll, sessionState);
        sessionState = calculateWindService.calculateWind(weatherRoll, sessionState);
        sessionState = calculateWindDirectionService.calculateWindDirection(weatherRoll, sessionState);

        sessionStateRepository.save(sessionState);

        int duration = calculateTimeService.calculateTime(timeRoll);

        System.out.println("Weather roll [" + weatherRoll + "]: duration " + duration + "h "
                + ", weather: " + sessionState.getWeather()
                + ", wind: " + sessionState.getWind()
                + ", wind direction: " + sessionState.getWindDirection());
    }

}
