package com.jrichardson.immunization.config.exceptions;

public class IMANotFoundException extends RuntimeException {

    private static final long serialVersionUID = 7124651937737031353L;

    public IMANotFoundException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public IMANotFoundException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public IMANotFoundException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public IMANotFoundException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public IMANotFoundException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }
}
