package com.demoproject.springmvc.service;

import com.demoproject.springmvc.dao.UserDao;
import com.demoproject.springmvc.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by nishat on 10/26/15.
 */
@Service
public class LogInService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(LogInService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.debug("------fetch User from DB And Authenticate -----");
        User user = userDao.getUserByName(s);
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), true, true, true, true, new HashSet<GrantedAuthority>());
    }
}
