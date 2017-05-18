package edu.orangecoastcollege.cs272.finalproject.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PlayAgainScene {
	
	@FXML
	private Button playAgainBtn;
	@FXML
	private Button closeBtn;
	
	@FXML
	public Object playAgainClick()
	{
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return null;
	}
	
	@FXML
	public Object closeClick()
	{
		System.exit(0);
		return null;
	}
}
