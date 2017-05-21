package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class MissileLaunchScene implements Initializable{
	
	@FXML
	private GridPane playerBoard;
	
	public Object continueGame()
	{
		return this;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerBoard = ViewNavigator.generateBoard(true);
	}
}
