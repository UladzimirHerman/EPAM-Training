package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserService;
import com.gmail.herman.uladzimir.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import static com.gmail.herman.uladzimir.command.AttributeName.*;
import static com.gmail.herman.uladzimir.route.ResponsePath.*;

/**
 * This class is used for the authentication process.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class LoginCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(LoginCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        String login = requestWrapper.getRequestParameter(LOGIN);
        String password = requestWrapper.getRequestParameter(PASSWORD);

        UserService userService = new UserServiceImpl();

        try {

            if (userService.isUserExist(login)) {
                User user = userService.findByLogin(login);

                if (BCrypt.checkpw(password, user.getPassword())) {
                    requestWrapper.putSessionAttribute(USER, user);
                    route.setResponseType(ResponseType.REDIRECT);
                    route.setResponsePath(REDIRECT_TO_LOGIN_PAGE);
                } else {
                    requestWrapper.putRequestAttribute(MESSAGE, true);
                    route.setResponsePath(FORWARD_TO_LOGIN_PAGE);
                }

            } else {
                requestWrapper.putRequestAttribute(MESSAGE, true);
                route.setResponsePath(FORWARD_TO_LOGIN_PAGE);
            }

        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}