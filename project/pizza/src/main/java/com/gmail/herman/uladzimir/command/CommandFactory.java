package com.gmail.herman.uladzimir.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Class {@link CommandFactory} is a factory, which produces the particular commands.
 *
 * @author Uladzimir Herman
 * @see Command
 * @see CommandType
 */
public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    private static final String INCORRECT_SYMBOL = "/";
    private static final String CORRECT_SYMBOL = "_";
    private static final int FIRST_SYMBOL = 1;

    /**
     * Defining command
     *
     * @param strCommand command name
     * @return particular command
     */
    public Command getCommand(String strCommand) {
        CommandType commandType = CommandType.EMPTY;

        if (strCommand == null || strCommand.isEmpty()) {
            return commandType.getCommand();
        }

        strCommand = exactCommand(strCommand);

        try {
            commandType = CommandType.valueOf(strCommand);
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Invalid command: " + strCommand);
        }

        return commandType.getCommand();
    }

    /**
     * Defining command name
     *
     * @param strCommand command name
     * @return correct command name
     */
    private String exactCommand(String strCommand) {

        if (strCommand.startsWith(INCORRECT_SYMBOL)) {
            strCommand = strCommand.substring(FIRST_SYMBOL);
        }

        strCommand = strCommand.replaceAll(INCORRECT_SYMBOL, CORRECT_SYMBOL).toUpperCase();

        return strCommand;
    }

}