package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.command.ResponsePath.FORWARD_TO_ADMIN_PRODUCT_CREATE_PAGE;

public class AdminProductCreateViewCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        Route route = new Route();
        route.setResponseType(ResponseType.FORWARD);
        route.setResponsePath(FORWARD_TO_ADMIN_PRODUCT_CREATE_PAGE);
        return route;
    }

}