package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;
import com.gmail.herman.uladzimir.entity.User;
import com.gmail.herman.uladzimir.entity.UserInfo;
import com.gmail.herman.uladzimir.exception.ServiceException;
import com.gmail.herman.uladzimir.service.UserInfoService;
import com.gmail.herman.uladzimir.service.impl.UserInfoServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.command.AttributeName.USER_INFO;
import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_PROFILE_PAGE;

/**
 * This class is used to get the profile edit page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class ProfileViewCommand implements Command {

    private static final Logger LOGGER =
            LogManager.getLogger(ProfileViewCommand.class);

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();

        UserInfoService userInfoService = new UserInfoServiceImpl();

        try {
            UserInfo userInfo = userInfoService.
                    findById(((User) requestWrapper.getSessionAttribute(USER)).getId());
            requestWrapper.putRequestAttribute(USER_INFO, userInfo);

            route.setResponsePath(FORWARD_TO_PROFILE_PAGE);
        } catch (ServiceException e) {
            LOGGER.error("ServiceException occurred when running the command: ", e);
        }

        return route;
    }

}