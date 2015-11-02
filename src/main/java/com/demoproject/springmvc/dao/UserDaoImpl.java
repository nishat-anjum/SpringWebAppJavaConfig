package com.demoproject.springmvc.dao;

import com.demoproject.springmvc.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nishat on 10/21/15.
 */
@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    @Transactional
    public User saveUser(User user) {
        hibernateTemplate.saveOrUpdate(user);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        List<User> users = (List<User>) hibernateTemplate.find("FROM User u WHERE u.email = ? ",
                new Object[]{name.toLowerCase()});
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public boolean isExistingEmailId(User user) {
        logger.debug("---------- Check if Email Already Exists------------");
        int count = DataAccessUtils.intResult(hibernateTemplate.find("SELECT COUNT(*) from User u WHERE u.email = ? "
                , new Object[]{user.getEmail().toLowerCase()}));
        return count > 0 ? true : false;
    }
}
