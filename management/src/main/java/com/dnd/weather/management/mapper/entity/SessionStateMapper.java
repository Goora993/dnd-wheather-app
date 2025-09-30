package com.dnd.weather.management.mapper.entity;

import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.management.business.bo.SessionStateBusinessObject;

public interface SessionStateMapper {

    // Entity -> BO
    SessionStateBusinessObject toBO(SessionState entity);

    // BO -> Entity
    SessionState toEntity(SessionStateBusinessObject bo);

}
