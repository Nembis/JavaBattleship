package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * This is where the program start
 * This loads the welcome Scene.
 */
public class MainView extends Application {

	/**
	 * This method loads up the welcome Scene.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Set stage only needs to be called once for the view navigator
		ViewNavigator.setStage(primaryStage);
		ViewNavigator.loadScene("Welcome to Battleships", ViewNavigator.WELCOME_SCENE);
	}

	/*
	 * Main method wher ethe prgram get's it start cammand.
	 */
	public static void main(String[] args) {
		launch(args);

	}

}
