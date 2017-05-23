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

/**
 * This class loads how the game went. 
 * If you lost it will tell you that you lsot and also hwo many turns it took for youto lose or win.
 * 
 * @author Dat Doan
 *
 */
public class GameOutcomeScene implements Initializable {

	private static Controller controller = Controller.getInstance();
	
	@FXML
	public Label outcomeLabel;
	@FXML
	public TextField nameField;
	@FXML
	public Button submitScoreBtn;
	
	/**
	 * This method creates teh new score and adds it into the observablelist and the data base.
	 * @return
	 */
	@FXML
	public Object submitScore()
	{
		ObservableList<Missile> playerMissiles = controller.getMissilesLaunched(true);
		int luckyMissiles = 0;
		for(Missile miss: playerMissiles)
			if(miss.isLucky())
				luckyMissiles++;
		String name = nameField.getText();
		if(controller.addScore(name, luckyMissiles, playerMissiles.size()))
		{
			controller.startNewGame();
			ViewNavigator.loadScene("Play Again", ViewNavigator.PLAY_AGAIN_SCENE);
		}
		return this;
	}
	@FXML
	public Object doNotSubmit()
	{
		controller.startNewGame();
		ViewNavigator.loadScene("Play Again", ViewNavigator.PLAY_AGAIN_SCENE);
		return this;
	}
	
	/**
	 * This mehtod loads up the scene before hand.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		if(controller.getShips(false).size() == 0)
		{
			outcomeLabel.setText("VICTORY!");
			outcomeLabel.setTextFill(Color.DARKGREEN);
			nameField.setDisable(false);
			submitScoreBtn.setDisable(false);
		}
		else
		{
			outcomeLabel.setText("DEFEAT!");
			outcomeLabel.setTextFill(Color.DARKRED);
			nameField.setDisable(true);
			submitScoreBtn.setDisable(true);
		}
		
	}

}
