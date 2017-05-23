package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GameOutcomeScene implements Initializable {

	private static Controller controller = Controller.getInstance();
	@FXML
	public static Label outcomeLabel;
	@FXML
	public static TextField nameField;
	@FXML
	public static Button submitScoreBtn;
	
	public Object submitScore()
	{
		ObservableList<Missile> playerMissiles = controller.getMissilesLaunched(true);
		int luckyMissiles = 0;
		for(Missile miss: playerMissiles)
			if(miss.isLucky())
				luckyMissiles++;
		String name = nameField.getText();
		if(controller.addScore(name, luckyMissiles, playerMissiles.size()))
			ViewNavigator.loadScene("Play Again", ViewNavigator.PLAY_AGAIN_SCENE);
		return this;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(controller.getShips(false).size() == 0)
		{
			outcomeLabel.setText("VICTORY!");
			outcomeLabel.setTextFill(Color.DARKGREEN);
		}
		else
		{
			outcomeLabel.setText("DEFEAT!");
			outcomeLabel.setTextFill(Color.DARKRED);
			nameField.setDisable(true);
		}
		
	}

}
