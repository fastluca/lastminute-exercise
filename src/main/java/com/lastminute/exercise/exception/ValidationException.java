/**
 * 
 */
package com.lastminute.exercise.exception;

/**
 * This exception is intended to propagate validation errors from UX.
 * 
 * @author Gianluca Colaianni
 *
 */
public class ValidationException extends RuntimeException {

    public static final String INVALID_INTEGER = "error.validation.integer";

    /**
     * 
     */
    private static final long serialVersionUID = 6448682273289981491L;

    /**
     * @param message
     */
    public ValidationException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public ValidationException(Throwable cause) {
	super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public ValidationException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

}
