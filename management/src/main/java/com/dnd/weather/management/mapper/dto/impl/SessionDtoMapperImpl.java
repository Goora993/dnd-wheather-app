package com.dnd.weather.management.mapper.dto.impl;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.dto.response.SessionDataResponse;
import com.dnd.weather.management.mapper.dto.SessionDtoMapper;

public class SessionDtoMapperImpl implements SessionDtoMapper {

    @Override
    public SessionDataResponse toResponse(SessionBusinessObject sessionBusinessObject) {
        return SessionDataResponse.builder()
                .sessionId(sessionBusinessObject.getId())
                .sessionName(sessionBusinessObject.getName())
                .sessionStateId(sessionBusinessObject.getStateBusinessObject().getId())
                .build();
    }

}
