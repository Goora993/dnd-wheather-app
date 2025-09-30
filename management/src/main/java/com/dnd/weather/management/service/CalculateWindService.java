package com.dnd.weather.management.service;


import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WindType;
import com.dnd.weather.persistence.repository.WindRollRuleJpaRepository;

public class CalculateWindService {

    private final WindRollRuleJpaRepository windRollRuleJpaRepository;

    public CalculateWindService(WindRollRuleJpaRepository windRollRuleJpaRepository) {
        this.windRollRuleJpaRepository = windRollRuleJpaRepository;
    }

    public SessionState calculateWind(int roll, SessionState sessionState) {

        WindType currentWind = sessionState.getWind();
        WindType resultWind = windRollRuleJpaRepository.findResultWind(currentWind, roll);

        sessionState.setWind(resultWind);

        return sessionState;
    }

}
