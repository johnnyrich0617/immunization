package com.jrichardson.immunization.config.exceptions;

public class IMAInvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 7124651937737031353L;

    /**
     *
     */
    public IMAInvalidRequestException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public IMAInvalidRequestException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public IMAInvalidRequestException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public IMAInvalidRequestException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public IMAInvalidRequestException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }
}
