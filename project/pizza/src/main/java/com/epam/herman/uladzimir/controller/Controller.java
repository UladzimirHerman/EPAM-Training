package com.epam.herman.uladzimir.controller;

import com.epam.herman.uladzimir.command.Command;
import com.epam.herman.uladzimir.command.CommandFactory;
import com.epam.herman.uladzimir.route.ResponseType;
import com.epam.herman.uladzimir.route.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.herman.uladzimir.command.AttributeName.COMMAND;

/**
 * Class {@link Controller} is the main controller of an application. It takes
 * all requests and manages their processing.
 *
 * @author Uladzimir Herman
 * @see RequestWrapper
 * @see Command
 * @see CommandFactory
 * @see Route
 */
public class Controller extends HttpServlet {

    private static final String ENCODING = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        executeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        executeRequest(req, resp);
    }

    /**
     * Executing get and post methods
     *
     * @param req  request
     * @param resp response
     * @throws IOException      may occur when setting encoding or taking new page
     * @throws ServletException may occur when executing forward
     */
    private void executeRequest(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        req.setCharacterEncoding(ENCODING);

        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.initialize(req);

        String strCommand = req.getParameter(COMMAND);

        if (strCommand == null) {
            strCommand = req.getRequestURI();
        }

        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommand(strCommand);

        Route route = command.execute(requestWrapper);
        requestWrapper.update(req);

        if (route.getResponseType() == ResponseType.REDIRECT) {
            resp.sendRedirect(route.getResponsePath());
        } else if (route.getResponseType() == ResponseType.FORWARD) {
            req.getRequestDispatcher(route.getResponsePath()).forward(req, resp);
        }

    }

}