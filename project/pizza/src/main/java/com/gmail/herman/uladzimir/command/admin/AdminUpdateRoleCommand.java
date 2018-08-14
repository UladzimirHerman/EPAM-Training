package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserRole;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserService;
import com.gmail.herman.uladzimir.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.USER_ID;
import static com.gmail.herman.uladzimir.command.AttributeName.USER_ROLE;
import static com.gmail.herman.uladzimir.command.ResponsePath.REDIRECT_TO_ADMIN_USERS_FIRST_PAGE;

public class AdminUpdateRoleCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(AdminUpdateRoleCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserService userService = new UserServiceImpl();

        try {
            User user = userService.
                    findById(Integer.parseInt(requestWrapper.getRequestParameter(USER_ID)));
            user.setUserRole(UserRole.valueOf(requestWrapper.getRequestParameter(USER_ROLE)));
            userService.update(user);
            route.setResponsePath(REDIRECT_TO_ADMIN_USERS_FIRST_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}