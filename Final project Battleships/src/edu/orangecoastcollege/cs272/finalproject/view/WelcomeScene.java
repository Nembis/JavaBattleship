package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;

public class WelcomeScene {

	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	
}