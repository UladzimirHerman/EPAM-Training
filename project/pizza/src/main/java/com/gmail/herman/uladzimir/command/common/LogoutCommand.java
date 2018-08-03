package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.command.AttributeName.USER;

public class LogoutCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        requestWrapper.removeSessionAttribute(USER);
        return new Route();
    }

}