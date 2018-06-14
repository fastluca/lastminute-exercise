/**
 * 
 */
package com.lastminute.exercise.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lastminute.exercise.utility.ResourceBundleHelper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * It is a centralized Exception handler responsible to intercept and manage all exceptions.
 * 
 * @author Gianluca Colaianni
 *
 */
public final class ExceptionHandler {
    
    private static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);
    
    private static final String ERROR_MESSAGE_KEY = "error.generic";
    
    private static ExceptionHandler instance;

    /**
     * 
     */
    private ExceptionHandler() {
	Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler::manageError);
    }
    
    /**
     * Global method to manage application exceptions.
     * @param t
     * @param e
     * @author Gianluca Colaianni
     */
    private static void manageError(Thread t, Throwable e) {
	log.error("Exception handled.", e);
	AlertType type = AlertType.ERROR;
	String title = "Error";
	String message = null;
	
	if (e instanceof ValidationException) {
	    type = AlertType.WARNING;
	    title = "Warning";
	    message = e.getMessage();
	} else {
	    message = ResourceBundleHelper.getString(ERROR_MESSAGE_KEY);
	}
	
	Alert alert = new Alert(type);
	alert.setTitle(title);
	alert.setHeaderText(message);
	
	alert.showAndWait();
	
    }
    
    /**
     * Initialize the global {@link ExceptionHandler} instance.
     * @return
     * @author Gianluca Colaianni
     */
    public static synchronized void initialize() {
	if (instance == null) {
	    instance = new ExceptionHandler();
	}
    }
    
    

}
