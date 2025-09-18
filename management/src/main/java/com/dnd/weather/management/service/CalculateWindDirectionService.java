package com.dnd.weather.management.service;


import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WindDirection;
import com.dnd.weather.dao.WindDirectionRollRuleDao;

public class CalculateWindDirectionService {

    private final WindDirectionRollRuleDao windDirectionRollRuleDao;

    public CalculateWindDirectionService(WindDirectionRollRuleDao windDirectionRollRuleDao) {
        this.windDirectionRollRuleDao = windDirectionRollRuleDao;
    }

    public SessionState calculateWindDirection(int roll, SessionState sessionState) {
        WindDirection currentWindDirection = sessionState.getWindDirection();
        WindDirection resultWindDirection = windDirectionRollRuleDao.findResultWindDirection(currentWindDirection, roll);

        sessionState.setWindDirection(resultWindDirection);

        return sessionState;
    }

}
