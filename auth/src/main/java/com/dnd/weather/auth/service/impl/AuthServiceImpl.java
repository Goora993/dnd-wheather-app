package com.dnd.weather.auth.service.impl;
import com.dnd.weather.auth.payload.request.LoginRequest;
import com.dnd.weather.auth.payload.request.RegisterRequest;
import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.auth.service.AuthService;
import com.dnd.weather.auth.service.JwtService;
import com.dnd.weather.auth.service.RefreshTokenService;
import com.dnd.weather.persistence.repository.UserDataJpaRepository;
import com.dnd.weather.domain.entity.RefreshToken;
import com.dnd.weather.domain.entity.UserData;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthServiceImpl implements AuthService {

    private final RefreshTokenService refreshTokenService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final UserDataJpaRepository userDataJpaRepository;

    private final PasswordEncoder encoder;

    public AuthServiceImpl(RefreshTokenService refreshTokenService, JwtService jwtService,
                           AuthenticationManager authenticationManager, UserDataJpaRepository userDataJpaRepository,
                           PasswordEncoder encoder) {
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDataJpaRepository = userDataJpaRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails createUserDetails(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return (UserDetailsImpl) authentication.getPrincipal();
    }

    @Override
    public Boolean doesUserExistByUsername(String username) {
        return userDataJpaRepository.existsByUsername(username);
    }

    @Override
    public Boolean doesUserExistByEmail(String email) {
        return userDataJpaRepository.existsByEmail(email);
    }

    @Override
    public ResponseCookie createRefreshTokenCookie(UserDetailsImpl userDetails) {
        RefreshToken refreshToken = refreshTokenService.getRefreshToken(userDetails.getId());
        return jwtService.generateRefreshJwtCookie(refreshToken.getToken());
    }

    @Transactional
    @Override
    public UserData registerNewUser(RegisterRequest registerRequest) {
        UserData userData = new UserData(registerRequest.getUsername(), encoder.encode(registerRequest.getPassword()),
                registerRequest.getEmail());

        return userDataJpaRepository.save(userData);
    }

}
