/**
 * 
 */
package com.lastminute.exercise;

import java.io.IOException;
import java.util.ResourceBundle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.exception.ExceptionHandler;
import com.lastminute.exercise.injection.CartModule;
import com.lastminute.exercise.utility.ResourceBundleHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is responsible to start the JavaFX application.
 * 
 * @author Gianluca Colaianni
 *
 */
public final class StartApplication extends Application {


    private static final String VIEW_NAME = "views/main.fxml";

    @Override
    public void start(Stage stage) throws IOException {
	
	/*
	 * Initialize global error handler.
	 */
	ExceptionHandler.initialize();
	
	final Injector injector = Guice.createInjector(new CartModule());
	
	setUserAgentStylesheet(STYLESHEET_CASPIAN);
	
	ResourceBundle bundle = ResourceBundleHelper.getBundle();
	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_NAME), bundle);
	loader.setControllerFactory(injector::getInstance);
	
	Parent root = loader.load();
	
	stage.setTitle(bundle.getString("label.application.title"));
	stage.setScene(new Scene(root));
	stage.setResizable(false);
	stage.show();

    }

    public static void main(String[] args) {
	launch(args);
    }

}
