package com.demoproject.springmvc.controllers;

import com.demoproject.springmvc.dao.UserDao;
import com.demoproject.springmvc.domain.ActionMessages;
import com.demoproject.springmvc.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nishat on 10/21/15.
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private static final Logger logger = Logger.getLogger(RegistrationController.class);

    private static final String MSG_TYPE_ERROR = "Error";
    private static final String MSG_TYPE_SUCCESS = "Success";

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ActionMessages saveNewUser(@RequestBody @Valid User user,
                                      BindingResult errors, HttpServletRequest request) {

        validateEmail(user, errors);

        if (errors.hasErrors()) {
            return new ActionMessages(MSG_TYPE_ERROR, generateErrorMessage(errors, request));
        } else {
            userDao.saveUser(user);
            return new ActionMessages(MSG_TYPE_SUCCESS, getSuccessMsg(request));
        }
    }

    private void validateEmail(User user, BindingResult errors) {
        if (userDao.isExistingEmailId(user)) {
            errors.rejectValue("email", null, "error.email.id.exists");
        }
    }

    private Map<String, String> generateErrorMessage(BindingResult errors, HttpServletRequest request) {
        Map<String, String> errorsMap = new HashMap<>();
        if (errors.hasFieldErrors()) {
            for (FieldError fieldError : errors.getFieldErrors()) {
                errorsMap.put(fieldError.getField(),
                        messageSource.getMessage(fieldError.getDefaultMessage(), null, request.getLocale()));
            }
        }
        return errorsMap;
    }

    public Map<String, String> getSuccessMsg(HttpServletRequest request) {
        Map<String, String> successMsgMap = new HashMap<>();
        successMsgMap.put("successMessage", messageSource.getMessage("success.save.new.user",
                null, request.getLocale()));
        return successMsgMap;
    }
}
