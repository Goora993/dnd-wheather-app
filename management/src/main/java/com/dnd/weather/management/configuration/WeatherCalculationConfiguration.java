package com.dnd.weather.management.configuration;

import com.dnd.weather.dao.*;
import com.dnd.weather.management.controller.WeatherController;
import com.dnd.weather.management.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherCalculationConfiguration {

    @Bean
    public CalculateWeatherService calculateWeatherService(WeatherRollRuleDao weatherRollRuleDao) {
        return new CalculateWeatherService(weatherRollRuleDao);
    }

    @Bean
    public CalculateWindService calculateWindService(WindRollRuleDao windRollRuleDao) {
        return new CalculateWindService(windRollRuleDao);
    }

    @Bean
    public CalculateWindDirectionService calculateWindDirectionService(WindDirectionRollRuleDao windDirectionRollRuleDao) {
        return new CalculateWindDirectionService(windDirectionRollRuleDao);
    }

    @Bean
    public CalculateTimeService calculateTimeService() {
        return new CalculateTimeService();
    }

    @Bean
    public CalculateWeatherFacade calculateWeatherFacade(SessionDao sessionDao,
                                                         SessionStateDao sessionStateDao,
                                                         CalculateWeatherService calculateWeatherService,
                                                         CalculateWindService calculateWindService,
                                                         CalculateWindDirectionService calculateWindDirectionService,
                                                         CalculateTimeService calculateTimeService) {
        return new CalculateWeatherFacade(sessionDao, sessionStateDao, calculateWeatherService,
                calculateWindService, calculateWindDirectionService, calculateTimeService);
    }

    @Bean
    public WeatherController weatherController(CalculateWeatherFacade calculateWeatherFacade) {
        return new WeatherController(calculateWeatherFacade);
    }

}
