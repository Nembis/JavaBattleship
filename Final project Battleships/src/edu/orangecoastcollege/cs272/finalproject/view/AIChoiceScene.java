package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class AIChoiceScene implements Initializable {

	private static Controller controller = Controller.getInstance();
	private int rowChoice, colChoice;
	private Random rNGMissile = new Random();
	private boolean fireLucky = controller.getDifficulty() != 0 && rNGMissile.nextBoolean();
	
	@FXML
	private Label choiceMessage;
	private GridPane playerBoard;
	
	private int[] selectInRange(int origX, int origY)
	{
		int[] rangeDiff = {0,0};
		do{
			rangeDiff[0] = rNGMissile.nextInt(5)-2;
			rangeDiff[1] = rNGMissile.nextInt(5)-2;
		}while(origX+rangeDiff[0] >=0 && origX+rangeDiff[0] <10 &&
				origY+rangeDiff[1] >=0 && origY+rangeDiff[1] <10);
		return rangeDiff;
	}
	
	private static ObservableList<Ship> playerActiveShips()
	{
		ObservableList<Ship> filtered = FXCollections.observableArrayList();
		ObservableList<Ship> allPlayerShips = controller.getShips(true);
		for(Ship boat: allPlayerShips)
			if(!boat.isDestroy())
				filtered.add(boat);
		return filtered;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(controller.getDifficulty() != 2)
		{
		do{
			rowChoice = rNGMissile.nextInt(10);
			colChoice = rNGMissile.nextInt(10);
		}while(rowChoice >=0 && rowChoice <10 && colChoice >=0 && colChoice <10);
		}
		else
		{
			ObservableList<Ship> shipsUp = playerActiveShips();
			Ship selectedShip = shipsUp.get(rNGMissile.nextInt(shipsUp.size()));
			int[] targetedLocation = selectInRange(selectedShip.getAphaCol()-'A', selectedShip.getNumRol());
			rowChoice = targetedLocation[0];
			colChoice = targetedLocation[1];
		}
		String colAlpha = "ABCDEFGHIJ";
		controller.addMissile(colAlpha.charAt(colChoice), rowChoice+1, false, fireLucky);
		int extraMissiles = rNGMissile.nextInt(4);
	}

}
