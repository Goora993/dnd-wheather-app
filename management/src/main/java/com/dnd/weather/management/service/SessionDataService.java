package com.dnd.weather.management.service;

import com.dnd.weather.management.business.bo.SessionBusinessObject;
import com.dnd.weather.management.business.bo.SessionStateBusinessObject;
import com.dnd.weather.management.business.repository.SessionRepository;
import com.dnd.weather.persistence.repository.SessionJpaRepository;
import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.domain.entity.SessionState;
import com.dnd.weather.domain.entity.UserData;
import com.dnd.weather.management.dto.request.CreateNewSessionRequest;
import com.dnd.weather.management.dto.response.SessionDataResponse;
import org.springframework.transaction.annotation.Transactional;

public class SessionDataService {

    private final CurrentUserService currentUserService;
    private final SessionJpaRepository sessionJpaRepository;
    private final SessionRepository sessionRepository;

    public SessionDataService(CurrentUserService currentUserService, SessionJpaRepository sessionJpaRepository, SessionRepository sessionRepository) {
        this.currentUserService = currentUserService;
        this.sessionJpaRepository = sessionJpaRepository;
        this.sessionRepository = sessionRepository;
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

        sessionRepository.save(sessionBusinessObject);


        Session session = new Session();
        session.setUserData(currentUser);
        session.setName(request.sessionName());

        SessionState sessionState = SessionState.builder()
                .session(session)
                .weather(request.weather())
                .wind(request.wind())
                .windDirection(request.windDirection())
                .hour(request.hour())
                .minute(request.minute())
                .build();

        session.setSessionState(sessionState);

        sessionJpaRepository.save(session);

        return new SessionDataResponse(session.getId(), sessionState.getId(), session.getName());
    }

}
