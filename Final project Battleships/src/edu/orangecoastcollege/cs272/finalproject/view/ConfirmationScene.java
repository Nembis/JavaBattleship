package edu.orangecoastcollege.cs272.finalproject.view;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;

public class ConfirmationScene {

	private Controller controller = Controller.getInstance();
	
	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	@FXML
	public Object restart(){
		
		
		
		return this;
	}
	
	@FXML
	public Object continueOldGame(){
		
		
		
		return this;
	}
	
}
