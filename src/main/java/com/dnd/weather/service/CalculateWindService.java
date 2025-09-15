package com.dnd.weather.service;

import com.dnd.weather.model.entity.SessionState;
import com.dnd.weather.model.enumeration.WindType;
import com.dnd.weather.repository.WindRollRuleRepository;

public class CalculateWindService {

    private final WindRollRuleRepository windRollRuleRepository;

    public CalculateWindService(WindRollRuleRepository windRollRuleRepository) {
        this.windRollRuleRepository = windRollRuleRepository;
    }

    public SessionState calculateWind(int roll, SessionState sessionState) {

        WindType currentWind = sessionState.getWind();
        WindType resultWind = windRollRuleRepository.findResultWind(currentWind, roll);

        sessionState.setWind(resultWind);

        return sessionState;
    }

}
