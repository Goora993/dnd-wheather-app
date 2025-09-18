package com.dnd.weather.auth.service;

import com.dnd.weather.domain.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    Optional<RefreshToken> findByToken(String token);

    RefreshToken getRefreshToken(Long userId);

    RefreshToken verifyExpiration(RefreshToken token);

    void removeRefreshToken(String token);

}
