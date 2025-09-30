package com.dnd.weather.persistence.repository;

import com.dnd.weather.domain.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserData_Id(Long userId);

}
