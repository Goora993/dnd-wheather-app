package com.dnd.weather.management.business.repository;

import com.dnd.weather.management.business.bo.SessionBusinessObject;

public interface SessionRepository {

    SessionBusinessObject save(SessionBusinessObject session);

}
