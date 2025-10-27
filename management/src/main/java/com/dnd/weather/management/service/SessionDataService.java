package com.dnd.weather.management.service;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.business.bo.SessionStateBusinessObject;
import com.dnd.weather.management.business.repository.SessionRepository;
import com.dnd.weather.management.mapper.dto.SessionDtoMapper;
import com.dnd.weather.domain.entity.UserData;
import com.dnd.weather.management.dto.request.CreateNewSessionRequest;
import com.dnd.weather.management.dto.response.SessionDataResponse;
import org.springframework.transaction.annotation.Transactional;

public class SessionDataService {

    private final CurrentUserService currentUserService;
    private final SessionRepository sessionRepository;
    private final SessionDtoMapper sessionDtoMapper;

    public SessionDataService(CurrentUserService currentUserService, SessionRepository sessionRepository, SessionDtoMapper sessionDtoMapper) {
        this.currentUserService = currentUserService;
        this.sessionRepository = sessionRepository;
        this.sessionDtoMapper = sessionDtoMapper;
    }

    @Transactional
    public SessionDataResponse createNewSessionData(CreateNewSessionRequest request) {
        UserData currentUser = currentUserService.getCurrentUser();

        SessionStateBusinessObject sessionStateBusinessObject = SessionStateBusinessObject.builder()
                .weather(request.weather())
                .wind(request.wind())
                .windDirection(request.windDirection())
                .hour(request.hour())
                .minute(request.minute())
                .build();

        SessionBusinessObject sessionBusinessObject = SessionBusinessObject.builder()
                .userData(currentUser)
                .name(request.sessionName())
                .stateBusinessObject(sessionStateBusinessObject)
                .build();

        return sessionDtoMapper.toResponse(sessionRepository.save(sessionBusinessObject));
    }

}
