package com.gmail.herman.uladzimir.command;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CommandFactory {

    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    private static final String INCORRECT_SYMBOL = "/";
    private static final String CORRECT_SYMBOL = "_";

    public Command getCommand(String strCommand) {
        CommandType commandType = CommandType.EMPTY;

        if (strCommand == null || strCommand.isEmpty()) {
            return commandType.getCommand();
        }

        strCommand = exactCommand(strCommand);

        try {
            commandType = CommandType.valueOf(strCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            LOGGER.warn("Invalid command: " + strCommand);
        }

        return commandType.getCommand();
    }

    private String exactCommand(String strCommand) {

        if (strCommand.startsWith(INCORRECT_SYMBOL)) {
            strCommand = strCommand.substring(1);
        }

        strCommand = strCommand.replaceAll(INCORRECT_SYMBOL, CORRECT_SYMBOL).toUpperCase();
        return strCommand;
    }

}