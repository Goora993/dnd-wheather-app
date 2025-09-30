package com.dnd.weather.management.mapper.entity;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.management.business.bo.SessionBusinessObject;
import org.springframework.stereotype.Component;

@Component
public interface SessionMapper {

    // Entity -> BO
    SessionBusinessObject toBO(Session entity);

    // BO -> Entity
    Session toEntity(SessionBusinessObject bo);

}
