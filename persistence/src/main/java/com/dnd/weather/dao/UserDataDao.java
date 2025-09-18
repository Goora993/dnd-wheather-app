package com.dnd.weather.dao;

import com.dnd.weather.domain.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataDao extends JpaRepository<UserData, Long> {

    Optional<UserData> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
