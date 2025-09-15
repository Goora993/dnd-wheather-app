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

    public CalculateWeatherFacade(SessionRepository sessionRepository,
                                  SessionStateRepository sessionStateRepository,
                                  CalculateWeatherService calculateWeatherService,
                                  CalculateWindService calculateWindService,
                                  CalculateWindDirectionService calculateWindDirectionService) {
        this.sessionRepository = sessionRepository;
        this.sessionStateRepository = sessionStateRepository;
        this.calculateWeatherService = calculateWeatherService;
        this.calculateWindService = calculateWindService;
        this.calculateWindDirectionService = calculateWindDirectionService;
    }

    @Transactional
    public void calculateWeather(int roll, long userId) {
        Session session = sessionRepository.findByUserDataId(userId);
        SessionState sessionState = sessionStateRepository.findBySession(session);

        sessionState = calculateWeatherService.calculateWeather(roll, sessionState);
        sessionState = calculateWindService.calculateWind(roll, sessionState);
        sessionState = calculateWindDirectionService.calculateWindDirection(roll, sessionState);

        sessionStateRepository.save(sessionState);

        System.out.println("Roll " + roll + ": " + sessionState.getWeather()
                + " " + sessionState.getWind()
                + " " + sessionState.getWindDirection());
    }

}
