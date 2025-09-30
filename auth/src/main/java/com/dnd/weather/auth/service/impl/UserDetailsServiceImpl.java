package com.dnd.weather.auth.service.impl;

import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.persistence.repository.UserDataJpaRepository;
import com.dnd.weather.domain.entity.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDataJpaRepository userDataJpaRepository;

    public UserDetailsServiceImpl(UserDataJpaRepository userDataJpaRepository) {
        this.userDataJpaRepository = userDataJpaRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(userData);
    }
}
