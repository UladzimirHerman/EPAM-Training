package com.epam.herman.uladzimir.exception;

/**
 * Class {@link DAOException} is used to create exceptions, that occur
 * at the DAO level. It's the heir of the {@link Exception}.
 *
 * @author Uladzimir Herman
 */
public class DAOException extends Exception {

    /**
     * Constructs a new exception with the specified detail message
     *
     * @param message detail message
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause
     *
     * @param cause specified cause
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause
     *
     * @param message detail message
     * @param cause   specified cause
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}