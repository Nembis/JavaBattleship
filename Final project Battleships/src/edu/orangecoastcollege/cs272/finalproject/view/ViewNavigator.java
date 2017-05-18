package edu.orangecoastcollege.cs272.finalproject.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewNavigator {
	public static final String DIFFICULTY_SCENE = "DifficultyScene.fxml";
	public static final String CONFIRMATION_SCENE = "ConfirmationScene.fxml";
	public static final String DIFFICULTY_REVIEW_SCENE = "DifficultyReviewScene.fxml";
	public static final String WELCOME_SCENE = "WelcomeScene.fxml";
	public static final String PLAY_AGAIN_SCENE = "PlayAgainScene.fxml";

	public static Stage mainStage;

	public static void setStage(Stage stage) {
		mainStage = stage;
	}

	public static void loadScene(String title, String sceneFXML) {

		try {
			mainStage.setTitle(title);
			Scene scene = new Scene(FXMLLoader.load(ViewNavigator.class.getResource(sceneFXML)));
			mainStage.setScene(scene);
			mainStage.show();
		} catch (IOException e) {
			System.err.println("Error loading: " + sceneFXML + "\n" + e.getMessage());
			e.printStackTrace();
		}
	}

}
