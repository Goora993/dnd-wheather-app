package com.dnd.weather.dao;


import com.dnd.weather.domain.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDao extends JpaRepository<Session, Long> {

    Session findByUserDataId(long userId);

}
