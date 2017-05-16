package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;

public class DifficultyReviewScene {

	
	
	@FXML
	public Object loadDifficultyScene() {
		
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_REVIEW_SCENE);
		
		return this;
	}

}
