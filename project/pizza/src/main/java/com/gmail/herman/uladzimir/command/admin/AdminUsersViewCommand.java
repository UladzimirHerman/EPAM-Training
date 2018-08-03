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

import java.util.List;

import static com.gmail.herman.uladzimir.command.AttributeName.USERS;
import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_USERS_PAGE;

public class AdminUsersViewCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminUsersViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserService userService = new UserServiceImpl();

        try {
            List<User> users = userService.findAllUserAndUserInfo();
            requestWrapper.putRequestAttribute(USERS, users);
            route.setResponseType(ResponseType.FORWARD);
            route.setResponsePath(FORWARD_TO_ADMIN_USERS_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}