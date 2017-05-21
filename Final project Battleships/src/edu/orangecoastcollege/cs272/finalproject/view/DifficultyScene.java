package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class DifficultyScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<String> difficultyCB;

	@FXML
	public Object difficultyClick() {
		
		try {
			if (controller.checkIfGameAlreadyExists(difficultyCB.getSelectionModel().getSelectedIndex())) {
				ViewNavigator.loadScene("Confirmation Scene", ViewNavigator.CONFIRMATION_SCENE);
				return this;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ViewNavigator.loadScene("", ViewNavigator.DIFFICULTY_REVIEW_SCENE);

		return this;
	}

	@FXML
	private Object toHighScore()
	{
		ViewNavigator.loadScene("High Scores", ViewNavigator.HIGH_SCORE_SCENE);
		return this;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> difficulties = FXCollections.observableArrayList();
		difficulties.add("Easy");
		difficulties.add("Normal");
		difficulties.add("Hard");
		difficultyCB.setItems(difficulties);
		difficultyCB.getSelectionModel().select("Easy");
	}
}
