package com.gmail.herman.uladzimir.command.common;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

public class EmptyCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        return new Route();
    }

}