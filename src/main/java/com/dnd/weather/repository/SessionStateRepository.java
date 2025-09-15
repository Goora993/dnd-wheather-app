package com.dnd.weather.repository;

import com.dnd.weather.model.entity.Session;
import com.dnd.weather.model.entity.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionStateRepository extends JpaRepository<SessionState, Long> {

    SessionState findBySession(Session session);

}
