package com.dnd.weather.management.service;


import com.dnd.weather.domain.enumeration.WindType;
import com.dnd.weather.persistence.repository.WindRollRuleJpaRepository;

public class CalculateWindService {

    private final WindRollRuleJpaRepository windRollRuleJpaRepository;

    public CalculateWindService(WindRollRuleJpaRepository windRollRuleJpaRepository) {
        this.windRollRuleJpaRepository = windRollRuleJpaRepository;
    }

    public WindType calculateWind(int roll, WindType currentWind) {
        return windRollRuleJpaRepository.findResultWind(currentWind, roll);
    }

}
