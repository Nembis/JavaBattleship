package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * This class let's teh player to choose which level they want to play on.
 * This also allows the player to see all the high scores.
 * @author Dat Doan
 */
public class DifficultyScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<String> difficultyCB;

	/**
	 * This method loads the next scene.
	 * This method can load up two Scene.
	 * the Confirmation Scene
	 * GameSetupScene
	 * @return
	 */
	@FXML
	public Object difficultyClick() {
		controller.setDifficulty(difficultyCB.getSelectionModel().getSelectedIndex());
		if (controller.checkIfGameAlreadyExists())
			ViewNavigator.loadScene("Confirmation Scene", ViewNavigator.CONFIRMATION_SCENE);
		else
			ViewNavigator.loadScene("", ViewNavigator.DIFFICULTY_REVIEW_SCENE);
		return this;
	}

	/**
	 * This method loads up the highschool method.
	 * @return
	 */
	@FXML
	private Object toHighScore()
	{
		ViewNavigator.loadScene("High Scores", ViewNavigator.HIGH_SCORE_SCENE);
		return this;
	}
	
	/**
	 * This method loads  up everythign before anything else.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> difficulties = FXCollections.observableArrayList();
		difficulties.add("Easy");
		difficulties.add("Normal");
		difficulties.add("Hard");
		difficultyCB.setItems(difficulties);
		difficultyCB.getSelectionModel().select(0);
	}
}
