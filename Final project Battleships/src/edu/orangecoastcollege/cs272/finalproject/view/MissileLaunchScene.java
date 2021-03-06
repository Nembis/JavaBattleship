package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * This class shows where your missile hit.
 * @author Dat Doan
 *
 */
public class MissileLaunchScene implements Initializable{
	
	private static Controller controller = Controller.getInstance();
	
	@FXML
	private GridPane playerBoard;
	@FXML
	private Label launchMessage;
	
	/**
	 * This mehod continues the game when you are done seeing wher you hit.
	 * @return
	 */
	@FXML
	public Object continueGame()
	{
		if(controller.getShips(false).size() == 0)
			ViewNavigator.loadScene("You Won", ViewNavigator.GAME_OUTCOME_SCENE);
		else
			ViewNavigator.loadScene("AI's Turn", ViewNavigator.AI_CHOICE_SCENE);
		return this;
	}

	private int[] selectInRange(int origX, int origY, int outRad, Random rNG)
	{
		int[] rangeDiff = {0,0};
		do{
			rangeDiff[0] = rNG.nextInt(2*outRad+1)-outRad;
			rangeDiff[1] = rNG.nextInt(2*outRad+1)-outRad;
		}while(origX+rangeDiff[0] >=0 && origX+rangeDiff[0] <10 &&
				origY+rangeDiff[1] >=0 && origY+rangeDiff[1] <10 &&
						(!controller.isValideMissileLaunch((char)(Character.getNumericValue('A')+origX + rangeDiff[0]), origY + rangeDiff[1]+1, true)));
		return rangeDiff;
	}
	
	private ObservableList<Ship> aIActiveShips()
	{
		ObservableList<Ship> aiShips = controller.getShips(true);
		ObservableList<Ship> active = FXCollections.observableArrayList();
		for(Ship boat:aiShips)
			if(!boat.isDestroy())
				active.add(boat);
		return active;
	}
	
	/**
	 * This method loads in the data before the scene oads up.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		launchMessage.setText("");
		ObservableList<Missile> playerMissiles = controller.getMissilesLaunched(true);
		Missile lastMissile = playerMissiles.get(playerMissiles.size()-1);
		boolean luckyMissile = lastMissile.isLucky(); 
		boolean targetHit = false;
		ObservableList<Ship> aiActive = aIActiveShips();
		Ship shipSelect = null;
		for(Ship boat: aiActive)
		{
			if(boat.getAphaCol()==lastMissile.getAphaCol() && 
					boat.getNumRol()==lastMissile.getNumRol())
			{
				targetHit = controller.wreckShip(shipSelect);
				break;
			}
		}
		
		if(luckyMissile)
		{
			controller.setLuckyMissiles(controller.getLuckyMissiles()-1);
			aiActive = aIActiveShips();
			Random rNG = new Random();
			int extraMissiles = rNG.nextInt(3)+1;
			int hit = 0;
			for(int m=0;m<extraMissiles;m++)
			{
				int[] location = selectInRange(lastMissile.getAphaCol()-'A', lastMissile.getNumRol()-1, 1, rNG);
				controller.addMissile((char)(Character.getNumericValue(lastMissile.getAphaCol()+location[0]))
						, lastMissile.getNumRol()+location[1], true, false, false);
				for(Ship boat: aiActive)
				{
					if(boat.getAphaCol() == (char)(Character.getNumericValue(lastMissile.getAphaCol())+location[0]) &&
							boat.getNumRol() == lastMissile.getNumRol()+location[1]);
					{
						controller.wreckShip(boat);
						hit++;
						break;
					}
				}
			}
			launchMessage.setText("You fired a lucky missile at "+lastMissile.getAphaCol()+
					lastMissile.getNumRol()+". "+String.valueOf(extraMissiles)+" more missiles launched with "+
					String.valueOf(hit)+" of them as hits.");
		}
		else
		{
			launchMessage.setText("You fired a missile at "+lastMissile.getAphaCol()+
					lastMissile.getNumRol()+". It was a "+(targetHit?"hit":"miss")+".");
		}
		for(int x=0;x<10;x++)
			for(int y=0;y<10;y++)
				playerBoard.add(ViewNavigator.generateSquare(false, x, y), x, y);
	}
}
