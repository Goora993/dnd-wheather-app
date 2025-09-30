package com.dnd.weather.management.business.repository.impl;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.business.repository.SessionRepository;
import com.dnd.weather.management.mapper.entity.SessionMapper;
import com.dnd.weather.persistence.repository.SessionJpaRepository;

public class SessionRepositoryImpl implements SessionRepository {

    private final SessionJpaRepository sessionJpaRepository;
    private final SessionMapper sessionMapper;

    public SessionRepositoryImpl(SessionJpaRepository sessionJpaRepository, SessionMapper sessionMapper) {
        this.sessionJpaRepository = sessionJpaRepository;
        this.sessionMapper = sessionMapper;
    }

    @Override
    public SessionBusinessObject save(SessionBusinessObject session) {
        return sessionMapper.toBO(sessionJpaRepository.save(sessionMapper.toEntity(session)));
    }

}
