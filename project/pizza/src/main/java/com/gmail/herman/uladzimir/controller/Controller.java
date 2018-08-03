package com.gmail.herman.uladzimir.controller;

import com.gmail.herman.uladzimir.command.Command;
import com.gmail.herman.uladzimir.command.CommandFactory;
import com.gmail.herman.uladzimir.command.ResponseType;
import com.gmail.herman.uladzimir.command.Route;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gmail.herman.uladzimir.command.AttributeName.COMMAND;

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