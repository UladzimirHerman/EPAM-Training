package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserService;
import com.gmail.herman.uladzimir.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.command.AttributeName.USER_ID;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_USERS_EDIT_PAGE;

public class AdminUsersEditViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminUsersEditViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserService userService = new UserServiceImpl();

        try {
            User user = userService.findUserAndUserInfoById
                    (Integer.parseInt(requestWrapper.getRequestParameter(USER_ID)));
            requestWrapper.putRequestAttribute(USER, user);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_USERS_EDIT_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}