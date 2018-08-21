package com.epam.herman.uladzimir.command;

import com.epam.herman.uladzimir.route.Route;
import com.epam.herman.uladzimir.controller.RequestWrapper;

/**
 * Interface {@link Command} is used to realize the command pattern.
 *
 * @author Uladzimir Herman
 * @see RequestWrapper
 * @see Route
 */
public interface Command {

    /**
     * Executing command
     *
     * @param requestWrapper object with necessary parameters and attributes
     * @return special route object
     */
    Route execute(RequestWrapper requestWrapper);

}