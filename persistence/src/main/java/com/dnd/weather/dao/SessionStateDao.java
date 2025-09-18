package com.dnd.weather.dao;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.domain.entity.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionStateDao extends JpaRepository<SessionState, Long> {

    SessionState findBySession(Session session);

}
