package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class DifficultyReviewScene {

	@FXML
	private Button toSetupSceneBtn;
	
	@FXML
	private Button toDifficultyBtn;
	
	@FXML
	public Object loadDifficultyScene() {
		
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	@FXML
	public Object loadSetupScene() {
		
		//ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_REVIEW_SCENE);
		
		return this;
	}

}
