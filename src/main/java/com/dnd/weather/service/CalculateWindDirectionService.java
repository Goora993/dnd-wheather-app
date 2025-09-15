package com.dnd.weather.service;

import com.dnd.weather.model.entity.SessionState;
import com.dnd.weather.model.enumeration.WindDirection;
import com.dnd.weather.repository.WindDirectionRollRuleRepository;

public class CalculateWindDirectionService {

    private final WindDirectionRollRuleRepository windDirectionRollRuleRepository;

    public CalculateWindDirectionService(WindDirectionRollRuleRepository windDirectionRollRuleRepository) {
        this.windDirectionRollRuleRepository = windDirectionRollRuleRepository;
    }

    public SessionState calculateWindDirection(int roll, SessionState sessionState) {
        WindDirection currentWindDirection = sessionState.getWindDirection();
        WindDirection resultWindDirection = windDirectionRollRuleRepository.findResultWindDirection(currentWindDirection, roll);

        sessionState.setWindDirection(resultWindDirection);

        return sessionState;
    }

}
