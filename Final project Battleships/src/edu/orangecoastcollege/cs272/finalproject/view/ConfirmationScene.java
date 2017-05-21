package edu.orangecoastcollege.cs272.finalproject.view;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;

public class ConfirmationScene {

	private Controller controller = Controller.getInstance()
	
	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	@FXML
	public Object startNewGame(){
		
		
		
		return this;
	}
	
	@FXML
	public Object continueOldGame(){
		
		
		
		return this;
	}
	
}
