package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.HighScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HighscoreScene implements Initializable{

	private static Controller controller = Controller.getInstance();
	
	private ListView<HighScore> easyScores;
	private ListView<HighScore> normScores;
	private ListView<HighScore> hardScores;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<HighScore> eScores = controller.getScores("Easy");
		FXCollections.sort(eScores);
		ObservableList<HighScore> nScores = controller.getScores("Normal");
		FXCollections.sort(nScores);
		ObservableList<HighScore> hScores = controller.getScores("Hard");
		FXCollections.sort(hScores);
		easyScores.setItems(eScores);
		normScores.setItems(nScores);
		hardScores.setItems(hScores);
		
	}
	
	public Object backToGame()
	{
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		return this;
	}

}
