package com.dnd.weather.management.service;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.business.repository.SessionRepository;
import com.dnd.weather.management.dto.request.RollWeatherRequest;
import com.dnd.weather.management.dto.response.CurrentWeatherResponse;
import com.dnd.weather.management.mapper.dto.CurrentWeatherDtoMapper;
import jakarta.transaction.Transactional;

public class CalculateWeatherFacade {

    private final SessionRepository sessionRepository;
    private final CurrentWeatherDtoMapper currentWeatherDtoMapper;
    private final CalculateWeatherService calculateWeatherService;
    private final CalculateWindService calculateWindService;
    private final CalculateWindDirectionService calculateWindDirectionService;
    private final CalculateTimeService calculateTimeService;

    public CalculateWeatherFacade(SessionRepository sessionRepository,
                                  CurrentWeatherDtoMapper currentWeatherDtoMapper,
                                  CalculateWeatherService calculateWeatherService,
                                  CalculateWindService calculateWindService,
                                  CalculateWindDirectionService calculateWindDirectionService,
                                  CalculateTimeService calculateTimeService) {
        this.sessionRepository = sessionRepository;
        this.currentWeatherDtoMapper = currentWeatherDtoMapper;
        this.calculateWeatherService = calculateWeatherService;
        this.calculateWindService = calculateWindService;
        this.calculateWindDirectionService = calculateWindDirectionService;
        this.calculateTimeService = calculateTimeService;
    }

    @Transactional
    public CurrentWeatherResponse calculateWeather(RollWeatherRequest rollWeatherRequest) {
        SessionBusinessObject sessionBusinessObject = sessionRepository.findByUserDataId(1L); // TODO: Handle user ids
        sessionBusinessObject.changeWeather(calculateWeatherService.calculateWeather(
                        rollWeatherRequest.weatherRoll(),
                        sessionBusinessObject.getCurrentWeather()
                )
        );
        sessionBusinessObject.changeWind(calculateWindService.calculateWind(
                        rollWeatherRequest.weatherRoll(),
                        sessionBusinessObject.getCurrentWind()
                )
        );
        sessionBusinessObject.changeWindDirection(calculateWindDirectionService.calculateWindDirection(
                        rollWeatherRequest.weatherRoll(),
                        sessionBusinessObject.getCurrentWindDirection()
                )
        );

        sessionRepository.save(sessionBusinessObject); // TODO: Error is thrown there, seems like I forgot version during mapping?


        int duration = calculateTimeService.calculateTime(rollWeatherRequest.timeRoll());

        System.out.println("Weather roll [" + rollWeatherRequest.weatherRoll() + "]: duration " + duration + "h "
                + ", weather: " + sessionBusinessObject.getCurrentWeather()
                + ", wind: " + sessionBusinessObject.getCurrentWind()
                + ", wind direction: " + sessionBusinessObject.getCurrentWindDirection()
        );

        return currentWeatherDtoMapper.toResponse(sessionBusinessObject);
    }

}
