package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Thsi class loads in a scene that welcomes the player and sys what game this is.
 * @author Dominic Mai
 *
 */
public class WelcomeScene {
	
	@FXML
	private Button mContinueBtn;
	
	/**
	 * This method load sthe welcome Scene.
	 * @return
	 */
	@FXML
	public Object loadDifficultyScene(){
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	
}
