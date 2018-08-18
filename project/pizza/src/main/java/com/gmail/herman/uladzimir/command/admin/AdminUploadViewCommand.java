package com.gmail.herman.uladzimir.command.admin;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.route.ResponseType;
import com.gmail.herman.uladzimir.route.Route;
import com.gmail.herman.uladzimir.controller.RequestWrapper;

import static com.gmail.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_UPLOAD_PAGE;

/**
 * This class is used to get the photo upload page.
 *
 * @author Uladzimir Herman
 * @see Command
 */
public class AdminUploadViewCommand implements Command {

    @Override
    public Route execute(RequestWrapper requestWrapper) {
        return new Route(ResponseType.FORWARD, FORWARD_TO_ADMIN_UPLOAD_PAGE);
    }

}