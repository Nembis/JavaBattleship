package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * This mehtod asks if you want to play the game again or do you want to close down the prgogram.
 * @author Dat Doan
 *
 */
public class PlayAgainScene {
	
	@FXML
	private Button playAgainBtn;
	@FXML
	private Button closeBtn;
	
	/**
	 * This method loads the difficulty scene again.
	 * @return
	 */
	@FXML
	public Object playAgainClick()
	{
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	/**
	 * this method close the program if the player doesn't want to play gain.
	 * @return
	 */
	@FXML
	public Object closeClick()
	{
		System.exit(0);
		return this;
	}
}
