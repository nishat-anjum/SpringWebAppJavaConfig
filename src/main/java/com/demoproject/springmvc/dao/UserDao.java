package com.demoproject.springmvc.dao;

import com.demoproject.springmvc.domain.User;

/**
 * Created by nishat on 10/21/15.
 */
public interface UserDao {
    User saveUser(User user);
    User getUserByName(String name);
    boolean isExistingEmailId(User user);
}
