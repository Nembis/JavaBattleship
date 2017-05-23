package edu.orangecoastcollege.cs272.finalproject.view;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;

/**
 *This class makes sure that the player knows that they will be doing.
 *If there is already a game in progress they player wil have three options.
 *Option 1 is yes which will restart a new game, optoin 2 is to continue old game, optoin 3 is go back to difficulty Scene.
 * @author Dominic Mai
 *
 */
public class ConfirmationScene {

	private Controller controller = Controller.getInstance();
	
	/**
	 * This method loads the Difficulty Scene.
	 * @return
	 */
	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	/**
	 * This mehtod cleans ou the data base on the difficulty that you selected
	 * after clean it out it will load the GameSetupScene.
	 * @return
	 */
	@FXML
	public Object restart(){
		
		controller.startNewGame();
		
		ViewNavigator.loadScene("Game Setup Scene", ViewNavigator.GAME_SETUP_SCENE);
		
		return this;
	}
	
	/**
	 * This mehtod reloads the game taht is already been playing.
	 * if the player wants to continue the old gam ethen this method will relaod it.
	 * @return
	 */
	@FXML
	public Object continueOldGame(){
		
		if(controller.getShips(false).size() < 10)
			ViewNavigator.loadScene("Game Setup Scene", ViewNavigator.GAME_SETUP_SCENE);
		else if(controller.getMissilesLaunched(true).size() > controller.getMissilesLaunched(false).size())
			ViewNavigator.loadScene("AI's Turn", ViewNavigator.AI_CHOICE_SCENE);
		else
			ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);
		
		return this;
	}
	
}
