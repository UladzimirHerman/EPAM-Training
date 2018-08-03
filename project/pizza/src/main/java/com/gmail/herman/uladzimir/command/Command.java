package com.gmail.herman.uladzimir.command;

import com.gmail.herman.uladzimir.controller.RequestWrapper;

public interface Command {

    Route execute(RequestWrapper requestWrapper);

}