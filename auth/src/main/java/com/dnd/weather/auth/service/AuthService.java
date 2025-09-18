package com.dnd.weather.auth.service;

import com.dnd.weather.auth.payload.request.LoginRequest;
import com.dnd.weather.auth.payload.request.RegisterRequest;
import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.domain.entity.UserData;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    UserDetails createUserDetails(LoginRequest loginRequest);

    Boolean doesUserExistByUsername(String username);

    Boolean doesUserExistByEmail(String email);

    UserData registerNewUser(RegisterRequest registerRequest);

    ResponseCookie createRefreshTokenCookie(UserDetailsImpl userDetails);

}
