package com.dnd.weather.management.configuration;

import com.dnd.weather.management.business.repository.SessionRepository;
import com.dnd.weather.management.business.repository.impl.SessionRepositoryImpl;
import com.dnd.weather.management.controller.WeatherController;
import com.dnd.weather.management.mapper.entity.SessionStateMapper;
import com.dnd.weather.management.mapper.entity.impl.SessionMapperImpl;
import com.dnd.weather.management.mapper.entity.impl.SessionStateMapperImpl;
import com.dnd.weather.management.service.*;
import com.dnd.weather.management.mapper.entity.SessionMapper;
import com.dnd.weather.persistence.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherCalculationConfiguration {

    @Bean
    public SessionStateMapper sessionStateMapper() {
        return new SessionStateMapperImpl();
    }

    @Bean
    public SessionMapper sessionMapper(SessionStateMapper sessionStateMapper) {
        return new SessionMapperImpl(sessionStateMapper);
    }

    @Bean
    public SessionRepository sessionRepository(SessionJpaRepository sessionJpaRepository, SessionMapper sessionMapper) {
        return new SessionRepositoryImpl(sessionJpaRepository, sessionMapper);
    }

    @Bean
    public CurrentUserService currentUserService(UserDataJpaRepository userDataJpaRepository) {
        return new CurrentUserService(userDataJpaRepository);
    }

    @Bean
    public SessionDataService sessionDataService(CurrentUserService currentUserService, SessionJpaRepository sessionJpaRepository, SessionRepository sessionRepository) {
        return new SessionDataService(currentUserService, sessionJpaRepository, sessionRepository);
    }

    @Bean
    public CalculateWeatherService calculateWeatherService(WeatherRollRuleJpaRepository weatherRollRuleJpaRepository) {
        return new CalculateWeatherService(weatherRollRuleJpaRepository);
    }

    @Bean
    public CalculateWindService calculateWindService(WindRollRuleJpaRepository windRollRuleJpaRepository) {
        return new CalculateWindService(windRollRuleJpaRepository);
    }

    @Bean
    public CalculateWindDirectionService calculateWindDirectionService(WindDirectionRollRuleJpaRepository windDirectionRollRuleJpaRepository) {
        return new CalculateWindDirectionService(windDirectionRollRuleJpaRepository);
    }

    @Bean
    public CalculateTimeService calculateTimeService() {
        return new CalculateTimeService();
    }

    @Bean
    public CalculateWeatherFacade calculateWeatherFacade(SessionJpaRepository sessionJpaRepository,
                                                         SessionStateJpaRepository sessionStateJpaRepository,
                                                         CalculateWeatherService calculateWeatherService,
                                                         CalculateWindService calculateWindService,
                                                         CalculateWindDirectionService calculateWindDirectionService,
                                                         CalculateTimeService calculateTimeService) {
        return new CalculateWeatherFacade(sessionJpaRepository, sessionStateJpaRepository, calculateWeatherService,
                calculateWindService, calculateWindDirectionService, calculateTimeService);
    }

    @Bean
    public WeatherController weatherController(CalculateWeatherFacade calculateWeatherFacade, SessionDataService sessionDataService) {
        return new WeatherController(calculateWeatherFacade, sessionDataService);
    }

}
