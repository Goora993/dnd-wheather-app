package com.dnd.weather.persistence.repository;

import com.dnd.weather.domain.entity.Session;
import com.dnd.weather.domain.entity.SessionState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionStateJpaRepository extends JpaRepository<SessionState, Long> {

    SessionState findBySession(Session session);

}
