package edu.orangecoastcollege.cs272.finalproject.view;

import java.io.IOException;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
		board.setPrefSize(32.0, 32.0);
		board.setMinSize(32.0, 32.0);
		board.setMaxSize(32.0, 32.0);
		ObservableList<Ship> ships = controller.getShips(player);
		for(int x=0;x<10;x++)
		{
			for(int y=0;y<10;y++)
			{
				for(Ship boat: ships)
				{
					if(boat.getNumRol()==y && Character.getNumericValue(boat.getAphaCol())-41==x)
					{
						if(boat.isDestroy())
							board.add(new ImageView("GridPics/BurningSquare.png"), x, y);
						else if(player)
							board.add(new ImageView("GridPics/ShipSquare.png"), x, y);
					}
					else
						board.add(new ImageView("GridPics/EmptySquare.png"), x, y);
				}
			}
		}
		return board;
	}
}
