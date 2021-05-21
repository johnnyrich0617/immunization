package com.jrichardson.immunization.config.exceptions;

public class IMAEntityRequestException extends RuntimeException {

    private static final long serialVersionUID = 7124651937737031353L;

    /**
     *
     */
    public IMAEntityRequestException() { }

    /**
     * @param message
     */
    public IMAEntityRequestException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public IMAEntityRequestException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public IMAEntityRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public IMAEntityRequestException(String message, Throwable cause, boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
