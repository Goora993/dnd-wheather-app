package com.dnd.weather.management.service;

import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.persistence.repository.UserDataJpaRepository;
import com.dnd.weather.domain.entity.UserData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CurrentUserService {

    private final UserDataJpaRepository userDataJpaRepository;

    public CurrentUserService(UserDataJpaRepository userDataJpaRepository) {
        this.userDataJpaRepository = userDataJpaRepository;
    }

    public UserData getCurrentUser() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        String username = userDetails.getUsername();

        return userDataJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
