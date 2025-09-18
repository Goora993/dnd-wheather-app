package com.dnd.weather.auth.service.impl;

import com.dnd.weather.auth.security.UserDetailsImpl;
import com.dnd.weather.dao.UserDataDao;
import com.dnd.weather.domain.entity.UserData;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDataDao userDataDao;

    public UserDetailsServiceImpl(UserDataDao userDataDao) {
        this.userDataDao = userDataDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData userData = userDataDao.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(userData);
    }
}
