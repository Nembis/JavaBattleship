package edu.orangecoastcollege.cs272.finalproject.view;

import java.io.IOException;
import java.io.InputStream;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ViewNavigator {
	public static final String DIFFICULTY_SCENE = "DifficultyScene.fxml";
	public static final String CONFIRMATION_SCENE = "ConfirmationScene.fxml";
	public static final String DIFFICULTY_REVIEW_SCENE = "DifficultyReviewScene.fxml";
	public static final String WELCOME_SCENE = "WelcomeScene.fxml";
	public static final String PLAY_AGAIN_SCENE = "PlayAgainScene.fxml";
	public static final String GAME_SETUP_SCENE = "GameSetupScene.fxml";
	public static final String HIGH_SCORE_SCENE = "HighscoreScene.fxml";
	public static final String MISSILE_LAUNCH_SCENE = "MissileLaunchScene.fxml";
	public static final String MAIN_GAME_SCENE = "MainGameScene.fxml";
	public static final String AI_CHOICE_SCENE = "AIChoiceScene.fxml";
	public static final String LUCKY_MISSILE_MINIGAME_SCENE = "LuckyMissileMinigameScene.fxml";
	public static final String GAME_OUTCOME_SCENE = "GameOutcomeScene.fxml";

	private static Controller controller = Controller.getInstance();
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

	public static GridPane generateBoard(boolean player)
	{
		GridPane board = new GridPane();
		board.setPrefSize(320.0, 320.0);
		board.setMinSize(320.0, 320.0);
		board.setMaxSize(320.0, 320.0);
		board.setGridLinesVisible(true);
		ObservableList<Ship> ships = controller.getShips(player);
		InputStream iStream = null;
		for(int x=0;x<10;x++)
		{
			for(int y=0;y<10;y++)
			{
				for(Ship boat: ships)
				{
					if(boat.getNumRol()==y && Character.getNumericValue(boat.getAphaCol()-'A')==x)
					{
						if(boat.isDestroy())
							iStream = ViewNavigator.class.getResourceAsStream("/resource/BurningSquare.jpg");
						else if(player)
							iStream = ViewNavigator.class.getResourceAsStream("/resource/ShipSquare.png");
					}
					else
						iStream = ViewNavigator.class.getResourceAsStream("/resource/EmptySquare.png");
					ImageView pic = new ImageView(new Image(iStream));
					board.add(pic, x, y);
				}
			}
		}
		return board;
	}
}
