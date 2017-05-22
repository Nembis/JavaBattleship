package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class DifficultyReviewScene implements Initializable{

	private int mDifficulty = Controller.getInstance().getDifficulty();
	
	@FXML
	private Label luckyNote;
	@FXML
	private Label aILucky;
	@FXML
	private Label aIMoveShip;
	
	@FXML
	public Object loadDifficultyScene() {
		
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);
		
		return this;
	}
	
	@FXML
	public Object loadSetupScene() {
		
		ViewNavigator.loadScene("Game Setup", ViewNavigator.GAME_SETUP_SCENE);
		
		return this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(mDifficulty == 0)
		{
			luckyNote.setText("You start with ONE lucky missile.");
			aILucky.setText("Your opponent is not able to use lucky missiles");
		}
		else
		{
			luckyNote.setText("You start with NO lucky missile.");
			aILucky.setText("Your opponent is able to use lucky missiles");
		}
		
		if(mDifficulty == 2)
			aIMoveShip.setText("Your opponent is able to move his own ships");
		else
			aIMoveShip.setText("Your opponent is not able to move his own ships");
		
	}

}
