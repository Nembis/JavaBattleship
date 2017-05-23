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
		
		controller.startNewGame();
		
		ViewNavigator.loadScene("Game Setup Scene", ViewNavigator.GAME_SETUP_SCENE);
		
		return this;
	}
	
	@FXML
	public Object continueOldGame(){
		
		if(controller.getShips(false).size() < 10)
			ViewNavigator.loadScene("Game Setup Scene", ViewNavigator.GAME_SETUP_SCENE);
		else if(controller.getShips(true).size() > controller.getShips(false).size())
			ViewNavigator.loadScene("AI's Turn", ViewNavigator.AI_CHOICE_SCENE);
		else
			ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);
		
		return this;
	}
	
}
