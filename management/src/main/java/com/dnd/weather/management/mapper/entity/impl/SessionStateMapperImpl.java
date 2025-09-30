package com.dnd.weather.management.mapper.entity.impl;

import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.management.business.bo.SessionStateBusinessObject;
import com.dnd.weather.management.mapper.entity.SessionStateMapper;

public class SessionStateMapperImpl implements SessionStateMapper {

    // Entity -> BO
    public SessionStateBusinessObject toBO(SessionState entity) {
        return SessionStateBusinessObject.builder()
                .id(entity.getId())
                .creationDate(entity.getCreationDate())
                .modificationDate(entity.getModificationDate())
                .weather(entity.getWeather())
                .wind(entity.getWind())
                .windDirection(entity.getWindDirection())
                .hour(entity.getHour())
                .minute(entity.getMinute())
                .build();
    }

    // BO -> Entity
    public SessionState toEntity(SessionStateBusinessObject bo) {
        return SessionState.builder()
                .id(bo.getId())
                .creationDate(bo.getCreationDate())
                .modificationDate(bo.getModificationDate())
                .weather(bo.getWeather())
                .wind(bo.getWind())
                .windDirection(bo.getWindDirection())
                .hour(bo.getHour())
                .minute(bo.getMinute())
                .build();
    }

}
