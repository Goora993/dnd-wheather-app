package com.dnd.weather.management.service;


import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.persistence.repository.WindDirectionRollRuleJpaRepository;

public class CalculateWindDirectionService {

    private final WindDirectionRollRuleJpaRepository windDirectionRollRuleJpaRepository;

    public CalculateWindDirectionService(WindDirectionRollRuleJpaRepository windDirectionRollRuleJpaRepository) {
        this.windDirectionRollRuleJpaRepository = windDirectionRollRuleJpaRepository;
    }

    public WindDirection calculateWindDirection(int roll, WindDirection currentWindDirection) {
        return windDirectionRollRuleJpaRepository.findResultWindDirection(currentWindDirection, roll);
    }

}
