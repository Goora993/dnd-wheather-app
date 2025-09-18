package com.dnd.weather.auth.controller;

import com.dnd.weather.auth.exception.TokenRefreshException;
import com.dnd.weather.auth.payload.request.LoginRequest;
import com.dnd.weather.auth.payload.request.RegisterRequest;
import com.dnd.weather.auth.payload.response.MessageResponse;
import com.dnd.weather.auth.payload.response.UserAuthResponse;
import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.auth.service.AuthService;
import com.dnd.weather.auth.service.JwtService;
import com.dnd.weather.auth.service.RefreshTokenService;
import com.dnd.weather.domain.entity.RefreshToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    public AuthController(JwtService jwtService, AuthService authService, RefreshTokenService refreshTokenService) {
        this.jwtService = jwtService;
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authService.createUserDetails(loginRequest);
        String accessToken = jwtService.generateJwtToken(userDetails.getUsername(), userDetails.getRoles());
        ResponseCookie jwtRefreshCookie = authService.createRefreshTokenCookie(userDetails);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserAuthResponse(accessToken));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {

        String requestRefreshToken = jwtService.getJwtRefreshFromCookies(request);

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserData)
                .map(userData -> {
                    String token = jwtService.generateJwtToken(userData.getUsername(), userData.getRoleNames());
                    return ResponseEntity.ok(new UserAuthResponse(token));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CookieValue("refreshToken") String refreshToken) {
        refreshTokenService.removeRefreshToken(refreshToken);
        ResponseCookie jwtRefreshCookie = jwtService.generateRefreshJwtCookie(null);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .build();
    }

    @Secured({"ADMIN"})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (authService.doesUserExistByUsername(registerRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (authService.doesUserExistByEmail(registerRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        authService.registerNewUser(registerRequest);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}