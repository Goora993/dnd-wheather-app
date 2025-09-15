package com.dnd.weather.configuration;

import com.dnd.weather.repository.*;
import com.dnd.weather.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherCalculationConfiguration {

    @Bean
    public CalculateWeatherService calculateWeatherService(WeatherRollRuleRepository weatherRollRuleRepository) {
        return new CalculateWeatherService(weatherRollRuleRepository);
    }

    @Bean
    public CalculateWindService calculateWindService(WindRollRuleRepository windRollRuleRepository) {
        return new CalculateWindService(windRollRuleRepository);
    }

    @Bean
    public CalculateWindDirectionService calculateWindDirectionService(WindDirectionRollRuleRepository windDirectionRollRuleRepository) {
        return new CalculateWindDirectionService(windDirectionRollRuleRepository);
    }

    @Bean
    public CalculateTimeService calculateTimeService() {
        return new CalculateTimeService();
    }

    @Bean
    public CalculateWeatherFacade calculateWeatherFacade(SessionRepository sessionRepository,
                                                         SessionStateRepository sessionStateRepository,
                                                         CalculateWeatherService calculateWeatherService,
                                                         CalculateWindService calculateWindService,
                                                         CalculateWindDirectionService calculateWindDirectionService,
                                                         CalculateTimeService calculateTimeService) {
        return new CalculateWeatherFacade(sessionRepository, sessionStateRepository, calculateWeatherService,
                calculateWindService, calculateWindDirectionService, calculateTimeService);
    }

}
