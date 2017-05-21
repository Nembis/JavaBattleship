package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MissileLaunchScene implements Initializable{
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private GridPane playerBoard;
	@FXML
	private Label launchMessage;
	
	public Object continueGame()
	{
		return this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		launchMessage.setText("");
		ObservableList<Missile> playerMissiles = controller.getMissilesLaunched(true);
		Missile lastMissile = playerMissiles.get(playerMissiles.size()-1);
		ObservableList<Ship> aiShips = controller.getShips(true);
		for(Ship boat: aiShips)
		{
			if(boat.getAphaCol()==lastMissile.getAphaCol() && 
					boat.getNumRol()==lastMissile.getNumRol() &&
					boat.getDifficulty() == lastMissile.getDifficulty())
			{
				launchMessage.setText("You fired a missile at "+lastMissile.getAphaCol()+
						lastMissile.getNumRol()+". It was a hit.");
			}
		}
		if(launchMessage.getText().equals(""))
			launchMessage.setText("You fired a missile at "+lastMissile.getAphaCol()+
					lastMissile.getNumRol()+". It was a miss.");
		playerBoard = ViewNavigator.generateBoard(true);
	}
}
