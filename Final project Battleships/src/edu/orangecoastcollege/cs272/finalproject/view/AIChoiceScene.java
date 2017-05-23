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
	private static Random rNGMissile = new Random();
	private static boolean fireLucky = controller.getDifficulty() != 0 && rNGMissile.nextBoolean();
	private boolean moveShip = controller.getDifficulty() == 2 && rNGMissile.nextBoolean();

	@FXML
	private Label choiceMessage;
	@FXML
	private GridPane playerBoard;

	private static int[] selectInRange(int origX, int origY, int outRad) {
		int[] rangeDiff = { 0, 0 };
		do {
			rangeDiff[0] = rNGMissile.nextInt(2 * outRad + 1) - outRad;
			rangeDiff[1] = rNGMissile.nextInt(2 * outRad + 1) - outRad;
		} while (origX + rangeDiff[0] >= 0 && origX + rangeDiff[0] < 10 && origY + rangeDiff[1] >= 0
				&& origY + rangeDiff[1] < 10 && 
				(!controller.isValideMissileLaunch((char)(Character.getNumericValue('A')+origX + rangeDiff[0]), origY + rangeDiff[1]+1, false)));
		return rangeDiff;
	}

	private static ObservableList<Ship> playerActiveShips() {
		ObservableList<Ship> filtered = FXCollections.observableArrayList();
		ObservableList<Ship> allPlayerShips = controller.getShips(true);
		for (Ship boat : allPlayerShips)
			if (!boat.isDestroy())
				filtered.add(boat);
		return filtered;
	}

	/**
	 * This loads teh next scene which is the main game scene.
	 * @return
	 */
	@FXML
	public Object toNextScene() {
		if (controller.getShips(true).size() == 0)
			ViewNavigator.loadScene("You Lost", ViewNavigator.GAME_OUTCOME_SCENE);
		else if (controller.getMissilesLaunched(true).size() % 2 == 0)
			ViewNavigator.loadScene("Lucky Missiles", ViewNavigator.LUCKY_MISSILE_MINIGAME_SCENE);
		else
			ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);
		return this;
	}

	private void missileFire() {
		int rowChoice, colChoice;
		if (controller.getDifficulty() != 2) {
			do {
				rowChoice = rNGMissile.nextInt(10);
				colChoice = rNGMissile.nextInt(10);
			} while (!controller.isValideMissileLaunch((char) (colChoice + 'A'), rowChoice + 1, false));
		} else {
			ObservableList<Ship> shipsUp = playerActiveShips();
			Ship selectedShip = shipsUp.get(rNGMissile.nextInt(shipsUp.size()));
			int[] targetedLocation = selectInRange(selectedShip.getAphaCol() - 'A', selectedShip.getNumRol(), 5);
			rowChoice = targetedLocation[0];
			colChoice = targetedLocation[1];
		}

		String colAlpha = "ABCDEFGHIJ";
		controller.addMissile(colAlpha.charAt(colChoice), rowChoice + 1, false, fireLucky, true);
		ObservableList<Ship> playerShips = playerActiveShips();
		boolean targetHit = false;
		for (Ship boat : playerShips)
			if (boat.getAphaCol() == colAlpha.charAt(colChoice) && boat.getNumRol() == rowChoice + 1) {
				controller.wreckShip(boat);
				targetHit = true;
				break;
			}
		if (fireLucky) {
			int extraMissiles = rNGMissile.nextInt(3) + 1;
			int hit = 0;
			for (int t = 0; t < extraMissiles; t++) {
				int[] otherTarget = selectInRange(colChoice, rowChoice, 1);
				controller.addMissile(colAlpha.charAt(otherTarget[0]+colChoice), otherTarget[1]+rowChoice+1, false, false, true);
				for (Ship boat : playerShips)
					if (boat.getAphaCol() == colAlpha.charAt(otherTarget[0]) && boat.getNumRol() == otherTarget[1]) {
						controller.wreckShip(boat);
						hit++;
					}
			}
			choiceMessage.setText("The AI fired a lucky missile at " + colAlpha.charAt(colChoice)
					+ String.valueOf(rowChoice + 1) + ". " + String.valueOf(extraMissiles)
					+ " more missiles launched with " + String.valueOf(hit) + " of them as hits.");
		} else {
			choiceMessage.setText("The AI fired a missile at " + String.valueOf(colAlpha.charAt(colChoice))
					+ String.valueOf(rowChoice + 1) + ". It was a " + (targetHit ? "hit" : "miss") + ".");
		}
	}

	private boolean movingShip() {
		ObservableList<Ship> aIShip = controller.getShips(false);
		ObservableList<Ship> aIActive = FXCollections.observableArrayList();
		for (Ship boat : aIShip) {
			aIActive.add(boat);
		}

		Ship selectShip;
		boolean shipMoved = false;
		do {
			selectShip = aIActive.get(rNGMissile.nextInt(aIActive.size()));
			shipMoved = controller.shipMove(selectShip);
			if (!shipMoved)
				aIActive.remove(selectShip);
		} while (!shipMoved || aIActive.isEmpty());

		if (shipMoved)
			choiceMessage.setText("The AI decided to move one of his ships");
		return shipMoved;
	}

	/**
	 * This method set's up the scene before anything happens.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		boolean moveError = false;
		if (moveShip)
			moveError = movingShip();
		if (!moveShip || moveError)
			missileFire();
		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++)
				playerBoard.add(ViewNavigator.generateSquare(true, x, y), x, y);
	}

}
