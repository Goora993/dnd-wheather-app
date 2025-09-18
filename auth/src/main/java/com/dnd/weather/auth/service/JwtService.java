package com.dnd.weather.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseCookie;

import java.util.List;


public interface JwtService {

    String generateJwtToken(String username, List<String> roles);

    String getUserNameFromJwtToken(String token);

    boolean validateJwtToken(String authToken);

    String getJwtRefreshFromCookies(HttpServletRequest request);

    ResponseCookie generateRefreshJwtCookie(String refreshToken);
}
