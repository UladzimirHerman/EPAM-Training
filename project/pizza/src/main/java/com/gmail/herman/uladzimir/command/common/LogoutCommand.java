package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;
import static com.gmail.herman.uladzimir.route.ResponsePath.REDIRECT_TO_LOGIN_PAGE;

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