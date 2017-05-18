package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;
import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.scene.control.Button;

public class WelcomeScene {
	
	@FXML
	private Button mContinueBtn;
	
	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	
}
