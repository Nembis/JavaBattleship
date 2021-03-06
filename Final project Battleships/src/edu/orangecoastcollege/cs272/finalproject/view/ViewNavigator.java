package edu.orangecoastcollege.cs272.finalproject.view;

import java.io.IOException;
import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * This class helsp moves scene to scene easier.
 */
public class ViewNavigator {
	/**
	 * These are staic methods that helps the view navigator to load in a scene.
	 */
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

	/**
	 * This method set's teh stage.
	 * @param stage
	 */
	public static void setStage(Stage stage) {
		mainStage = stage;
	}

	/**
	 * This method loads all teh scenes.
	 * @param title
	 * @param sceneFXML
	 */
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

	/**
	 * this method loads in the image of the water and the shps.
	 * @param player
	 * @param col
	 * @param row
	 * @return
	 */
	public static ImageView generateSquare(boolean player, int col, int row) {
		ObservableList<Ship> ships = controller.getShips(player);
		for (Ship boat : ships) {
			if (boat.getNumRol()-1 == row && (Character.getNumericValue(boat.getAphaCol())-Character.getNumericValue('A')) == col) {
				if (boat.isDestroy())
					return new ImageView(new Image(ViewNavigator.class.getResourceAsStream("..\\..\\..\\..\\..\\image\\BurningSquare.png")));
				else if(player)
					return new ImageView(new Image(ViewNavigator.class.getResourceAsStream("..\\..\\..\\..\\..\\image\\ShipSquare.png")));
			}
		}
		ObservableList<Missile> missiles = controller.getMissilesLaunched(!player);
		for(Missile m: missiles)
			if (m.getNumRol()-1 == row && (Character.getNumericValue(m.getAphaCol())-Character.getNumericValue('A')) == col) {
				return new ImageView(new Image(ViewNavigator.class.getResourceAsStream("..\\..\\..\\..\\..\\image\\MarkedSquare.png")));
			}
		return new ImageView(new Image(ViewNavigator.class.getResourceAsStream("..\\..\\..\\..\\..\\image\\EmptySquare.png")));
	}
}
