package com.dnd.weather.auth.service.impl;

import com.dnd.weather.auth.exception.TokenRefreshException;
import com.dnd.weather.auth.service.RefreshTokenService;
import com.dnd.weather.persistence.repository.RefreshTokenJpaRepository;
import com.dnd.weather.persistence.repository.UserDataJpaRepository;
import com.dnd.weather.domain.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${dndweather.app.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    private final UserDataJpaRepository userDataJpaRepository;

    public RefreshTokenServiceImpl(RefreshTokenJpaRepository refreshTokenJpaRepository, UserDataJpaRepository userDataJpaRepository) {
        this.refreshTokenJpaRepository = refreshTokenJpaRepository;
        this.userDataJpaRepository = userDataJpaRepository;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenJpaRepository.findByToken(token);
    }

    @Transactional
    @Override
    public RefreshToken getRefreshToken(Long userId) {
        Optional<RefreshToken> refreshToken = refreshTokenJpaRepository.findByUserData_Id(userId);
        return refreshToken.map(this::updateRefreshToken).orElseGet(() -> createRefreshToken(userId));
    }

    private RefreshToken updateRefreshToken(RefreshToken token) {
        token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        token.setToken(UUID.randomUUID().toString());
        return refreshTokenJpaRepository.save(token);
    }

    private RefreshToken createRefreshToken(Long userId) {
        RefreshToken token = new RefreshToken();
        token.setUserData(userDataJpaRepository.findById(userId).get()); // TODO 04.07.2024: Handle optional
        token.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        token.setToken(UUID.randomUUID().toString());
        return refreshTokenJpaRepository.save(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenJpaRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Override
    public void removeRefreshToken(String token) {
        refreshTokenJpaRepository.findByToken(token).ifPresent(refreshTokenJpaRepository::delete);
    }
}
