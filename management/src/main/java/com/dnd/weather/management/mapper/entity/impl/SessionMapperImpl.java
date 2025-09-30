package com.dnd.weather.management.mapper.entity.impl;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.mapper.entity.SessionMapper;
import com.dnd.weather.management.mapper.entity.SessionStateMapper;

public class SessionMapperImpl implements SessionMapper {

    private final SessionStateMapper sessionStateMapper;

    public SessionMapperImpl(SessionStateMapper sessionStateMapper) {
        this.sessionStateMapper = sessionStateMapper;
    }

    @Override
    public SessionBusinessObject toBO(Session entity) {
        return SessionBusinessObject.builder()
                .id(entity.getId())
                .creationDate(entity.getCreationDate())
                .modificationDate(entity.getModificationDate())
                .name(entity.getName())
                .stateBusinessObject(sessionStateMapper.toBO(entity.getSessionState()))
                .userData(entity.getUserData())
                .build();
    }

    @Override
    public Session toEntity(SessionBusinessObject bo) {
        return Session.builder()
                .id(bo.getId())
                .creationDate(bo.getCreationDate())
                .modificationDate(bo.getModificationDate())
                .name(bo.getName())
                .sessionState(sessionStateMapper.toEntity(bo.getStateBusinessObject()))
                .userData(bo.getUserData())
                .build();
    }
}
