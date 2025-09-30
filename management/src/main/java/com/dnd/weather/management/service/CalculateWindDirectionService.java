package com.dnd.weather.management.service;


import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.persistence.repository.WindDirectionRollRuleJpaRepository;

public class CalculateWindDirectionService {

    private final WindDirectionRollRuleJpaRepository windDirectionRollRuleJpaRepository;

    public CalculateWindDirectionService(WindDirectionRollRuleJpaRepository windDirectionRollRuleJpaRepository) {
        this.windDirectionRollRuleJpaRepository = windDirectionRollRuleJpaRepository;
    }

    public SessionState calculateWindDirection(int roll, SessionState sessionState) {
        WindDirection currentWindDirection = sessionState.getWindDirection();
        WindDirection resultWindDirection = windDirectionRollRuleJpaRepository.findResultWindDirection(currentWindDirection, roll);

        sessionState.setWindDirection(resultWindDirection);

        return sessionState;
    }

}
