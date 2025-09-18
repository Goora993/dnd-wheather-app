package com.dnd.weather.management.service;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.management.dto.request.RollWeatherRequest;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.dao.SessionDao;
import com.dnd.weather.dao.SessionStateDao;
import jakarta.transaction.Transactional;

public class CalculateWeatherFacade {

    private final SessionDao sessionDao;
    private final SessionStateDao sessionStateDao;
    private final CalculateWeatherService calculateWeatherService;
    private final CalculateWindService calculateWindService;
    private final CalculateWindDirectionService calculateWindDirectionService;
    private final CalculateTimeService calculateTimeService;

    public CalculateWeatherFacade(SessionDao sessionDao,
                                  SessionStateDao sessionStateDao,
                                  CalculateWeatherService calculateWeatherService,
                                  CalculateWindService calculateWindService,
                                  CalculateWindDirectionService calculateWindDirectionService,
                                  CalculateTimeService calculateTimeService) {
        this.sessionDao = sessionDao;
        this.sessionStateDao = sessionStateDao;
        this.calculateWeatherService = calculateWeatherService;
        this.calculateWindService = calculateWindService;
        this.calculateWindDirectionService = calculateWindDirectionService;
        this.calculateTimeService = calculateTimeService;
    }

    @Transactional
    public CurrentWeatherResponse calculateWeather(RollWeatherRequest rollWeatherRequest) {
        Session session = sessionDao.findByUserDataId(1);
        SessionState sessionState = sessionStateDao.findBySession(session);

        sessionState = calculateWeatherService.calculateWeather(rollWeatherRequest.weatherRoll(), sessionState);
        sessionState = calculateWindService.calculateWind(rollWeatherRequest.weatherRoll(), sessionState);
        sessionState = calculateWindDirectionService.calculateWindDirection(rollWeatherRequest.weatherRoll(), sessionState);

        sessionStateDao.save(sessionState);

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
