package com.dnd.weather.management.business.repository;

import com.dnd.weather.management.business.bo.SessionStateBusinessObject;

public interface SessionStateRepository {

    SessionStateBusinessObject save(SessionStateBusinessObject sessionState);

}
