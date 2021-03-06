package com.epam.herman.uladzimir.command.common;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

import static com.epam.herman.uladzimir.command.AttributeName.USER;
import static com.epam.herman.uladzimir.route.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

/**
 * This class is used to log off the system.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class LogoutCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        requestWrapper.removeSessionAttribute(USER);
        return new Route(ResponseType.REDIRECT, REDIRECT_TO_LOGIN_PAGE);
    }

}