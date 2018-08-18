package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserInfoService;
import com.gmail.herman.uladzimir.service.UserService;
import com.gmail.herman.uladzimir.service.impl.UserInfoServiceImpl;
import com.gmail.herman.uladzimir.service.impl.UserServiceImpl;
import com.gmail.herman.uladzimir.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_PROFILE_PAGE;

/**
 * This class is used to change password.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class PasswordEditCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(PasswordEditCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        User user = (User) requestWrapper.getSessionAttribute(USER);

        String currentPassword = requestWrapper.getRequestParameter(PASSWORD);
        String newPassword = requestWrapper.getRequestParameter(NEW_PASSWORD);
        String newPasswordRepeat = requestWrapper.getRequestParameter(NEW_PASSWORD_REPEAT);

        UserValidator userValidator = new UserValidator();

        try {

            if (userValidator.isPasswordChangeCorrect
                    (currentPassword, user.getPassword(), newPassword, newPasswordRepeat)) {
                user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));

                UserService userService = new UserServiceImpl();
                userService.update(user);

                requestWrapper.putSessionAttribute(USER, user);
                route.setResponseType(ResponseType.REDIRECT);
                route.setResponsePath(requestWrapper.getReferer());
            } else {
                UserInfoService userInfoService = new UserInfoServiceImpl();
                UserInfo userInfo = userInfoService.findById(user.getId());

                requestWrapper.putRequestAttribute(MESSAGE, true);
                requestWrapper.putRequestAttribute(USER_INFO, userInfo);
                route.setResponsePath(FORWARD_TO_PROFILE_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}