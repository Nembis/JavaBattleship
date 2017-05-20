package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.HighScore;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class HighscoreScene implements Initializable{

	private static Controller controller = Controller.getInstance();
	
	private ListView<HighScore> easyScores;
	private ListView<HighScore> normScores;
	private ListView<HighScore> hardScores;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		easyScores.setItems(controller.getScores("Easy"));
		normScores.setItems(controller.getScores("Normal"));
		hardScores.setItems(controller.getScores("Hard"));
	}
	
	public Object backToGame()
	{
		String title;
		switch(controller.getDifficulty())
		{
		case 0:
			title = "Launch your missile: Easy";
			break;
		case 1:
			title = "Launch your missile: Normal";
			break;
		default:
			title = "Launch your missile: Hard";
		}
		//ViewNavigator.loadScene(title, ViewNavigator.);
		return this;
	}

}
