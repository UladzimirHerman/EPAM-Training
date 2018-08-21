package com.epam.herman.uladzimir.command.admin;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

import static com.epam.herman.uladzimir.route.ResponsePath.FORWARD_TO_ADMIN_UPLOAD_PAGE;

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