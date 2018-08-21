package com.epam.herman.uladzimir.exception;

/**
 * Class {@link ServiceException} is used to create exceptions, that occur
 * at the service level. It's the heir of the {@link Exception}.
 *
 * @author Uladzimir Herman
 */
public class ServiceException extends Exception {

    /**
     * Constructs a new exception with the specified detail message
     *
     * @param message detail message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause
     *
     * @param cause specified cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new exception with the specified detail message and cause
     *
     * @param message detail message
     * @param cause   specified cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}