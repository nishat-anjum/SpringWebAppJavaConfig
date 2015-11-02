package com.demoproject.springmvc.controllers;

import com.demoproject.springmvc.dao.UserDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by nishat on 10/23/15.
 */
@Controller
public class LogInController {

    private static final Logger logger = Logger.getLogger(LogInController.class);

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String logIn() {
        if (checkIfAlreadyLoggedIn()) {
            return "redirect:/profile";
        }

        return "logInPage";
    }

    @RequestMapping(value = "/login", params = "error", method = RequestMethod.GET)
    public String logIn(ModelMap modelMap) {
        if (checkIfAlreadyLoggedIn()) {
            return "redirect:/profile";  // if user makes any GET request manually at this handler from browser
        }

        modelMap.put("errorKey", "error.invalid.login");
        return "logInPage";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    protected String showProfile(ModelMap modelMap) {
        if (checkIfAlreadyLoggedIn()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelMap.put("user", userDao.getUserByName(user.getUsername()));
            logger.debug("--------> " + "fetched user by Email ID"); // log will be print in console
            return "profile";
        } else {
            return "redirect:/login";
        }
    }

    private boolean checkIfAlreadyLoggedIn() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !(auth instanceof AnonymousAuthenticationToken);
    }
}
