/**
 * 
 */
package com.lastminute.exercise;

import java.io.IOException;
import java.util.ResourceBundle;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.lastminute.exercise.exception.ExceptionHandler;
import com.lastminute.exercise.injection.CarModule;
import com.lastminute.exercise.utility.ResourceBundleHelper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Gianluca Colaianni -- Fincons Group S.p.A.
 *
 */
public final class StartApplication extends Application {


    private static final String VIEW_NAME = "views/view.fxml";

    @Override
    public void start(Stage stage) throws IOException {
	
	/*
	 * Initialize global error handler.
	 */
	ExceptionHandler.initialize();
	
	final Injector injector = Guice.createInjector(new CarModule());
	
	setUserAgentStylesheet(STYLESHEET_CASPIAN);
	
	ResourceBundle bundle = ResourceBundleHelper.getBundle();
	FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(VIEW_NAME), bundle);
	loader.setControllerFactory(injector::getInstance);
	
	Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(VIEW_NAME), bundle);
	
	stage.setTitle(bundle.getString("application.title"));
	stage.setScene(new Scene(root, 600, 400));
	stage.setResizable(false);
	stage.show();

    }

    public static void main(String[] args) {
	launch(args);
    }

}
