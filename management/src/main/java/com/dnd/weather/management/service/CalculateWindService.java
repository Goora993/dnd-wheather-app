package com.dnd.weather.management.service;


import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.enumeration.WindType;
import com.dnd.weather.dao.WindRollRuleDao;

public class CalculateWindService {

    private final WindRollRuleDao windRollRuleDao;

    public CalculateWindService(WindRollRuleDao windRollRuleDao) {
        this.windRollRuleDao = windRollRuleDao;
    }

    public SessionState calculateWind(int roll, SessionState sessionState) {

        WindType currentWind = sessionState.getWind();
        WindType resultWind = windRollRuleDao.findResultWind(currentWind, roll);

        sessionState.setWind(resultWind);

        return sessionState;
    }

}
