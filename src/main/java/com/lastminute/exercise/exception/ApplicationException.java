/**
 * 
 */
package com.lastminute.exercise.exception;

/**
 * Wrapper for all managed exceptions.
 * 
 * @author Gianluca Colaianni
 *
 */
public class ApplicationException extends RuntimeException {


    /**
     * 
     */
    private static final long serialVersionUID = 2061053247451424290L;

    /**
     * @param message
     */
    public ApplicationException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public ApplicationException(Throwable cause) {
	super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ApplicationException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ApplicationException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
