package edu.orangecoastcollege.cs272.finalproject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import edu.orangecoastcollege.cs272.finalproject.model.DBModel;
import edu.orangecoastcollege.cs272.finalproject.model.HighScore;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "gameData.db";

	private static final String MISSILE_E_TABLE_NAME = "easy_missiles";
	private static final String MISSILE_N_TABLE_NAME = "norm_missiles";
	private static final String MISSILE_H_TABLE_NAME = "hard_missiles";
	private static final String[] MISSILE_FIELD_NAMES = { "id", "col", "row", "player_owned", "lucky" };
	private static final String[] MISSILE_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "INTEGER",
			"INTEGER" };

	private static final String SHIP_E_TABLE_NAME = "easy_ships";
	private static final String SHIP_N_TABLE_NAME = "norm_ships";
	private static final String SHIP_H_TABLE_NAME = "hard_ships";
	private static final String[] SHIP_FIELD_NAMES = { "id", "col", "row", "player_owned", "down" };
	private static final String[] SHIP_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "INTEGER", "INTEGER" };

	private static final String SCORE_TABLE_NAME = "high_scores";
	private static final String[] SCORE_FIELD_NAMES = { "id", "name", "turns", "difficulty", "luckys" };
	private static final String[] SCORE_FIELD_TYPES = { "INTEGER PRIMARY KEY", "TEXT", "INTEGER", "TEXT", "INTEGER" };

	private int mDifficulty;
	private int mLuckyMissiles;
	private DBModel mEasyShipsDB;
	private DBModel mEasyMissilesDB;
	private DBModel mNormShipsDB;
	private DBModel mNormMissilesDB;
	private DBModel mHardShipsDB;
	private DBModel mHardMissilesDB;
	private DBModel mHighScoreDB;

	private ObservableList<Missile> mAllMissileList;
	private ObservableList<Ship> mAllShipList;
	private ObservableList<HighScore> mScoreList;

	private Controller() {
	}

	/**
	 * This methods get's the 
	 * This also creates a new DB if there is not one.
	 * @return
	 */
	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllMissileList = FXCollections.observableArrayList();
			theOne.mAllShipList = FXCollections.observableArrayList();
			theOne.mScoreList = FXCollections.observableArrayList();

			try {
				theOne.mEasyMissilesDB = new DBModel(DB_NAME, MISSILE_E_TABLE_NAME, MISSILE_FIELD_NAMES,
						MISSILE_FIELD_TYPES);
				ArrayList<ArrayList<String>> records = theOne.mEasyMissilesDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 0, lucky));
				}

				theOne.mNormMissilesDB = new DBModel(DB_NAME, MISSILE_N_TABLE_NAME, MISSILE_FIELD_NAMES,
						MISSILE_FIELD_TYPES);
				records = theOne.mNormMissilesDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 1, lucky));
				}

				theOne.mHardMissilesDB = new DBModel(DB_NAME, MISSILE_H_TABLE_NAME, MISSILE_FIELD_NAMES,
						MISSILE_FIELD_TYPES);
				records = theOne.mHardMissilesDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 2, lucky));
				}

				theOne.mEasyShipsDB = new DBModel(DB_NAME, SHIP_E_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mEasyShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 0, down));
				}

				theOne.mNormShipsDB = new DBModel(DB_NAME, SHIP_N_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mNormShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 1, down));
				}

				theOne.mHardShipsDB = new DBModel(DB_NAME, SHIP_H_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mHardShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 2, down));
				}

				theOne.mHighScoreDB = new DBModel(DB_NAME, SCORE_TABLE_NAME, SCORE_FIELD_NAMES, SCORE_FIELD_TYPES);
				records = theOne.mHighScoreDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					String name = rs.get(1);
					int turns = Integer.parseInt(rs.get(1));
					String diff = rs.get(2);
					int lucks = Integer.parseInt(rs.get(3));
					theOne.mScoreList.add(new HighScore(id, name, diff, turns, lucks));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}

	/**
	 * This method get all the Missile Launched. 
	 * This method Finds the playerrs missile Launched
	 * There is a overLoad method.
	 * @param player
	 * @return
	 */
	public ObservableList<Missile> getMissilesLaunched(boolean player) {
		ObservableList<Missile> missiles = FXCollections.observableArrayList();
		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.isPlayer() == player && rocket.getDifficulty() == theOne.mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	/**
	 * This method get's all the missile launched
	 * This one is an overloaded method and it doesn't take in a arg.
	 * It will return every missile from both the player and the AI in the same difficulty.
	 * @return
	 */
	public ObservableList<Missile> getMissilesLaunched() {
		ObservableList<Missile> missiles = FXCollections.observableArrayList();
		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.getDifficulty() == theOne.mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	/**
	 * This method get's the ships of the Player in a difficulty.
	 * There is an overloaded method of this.
	 * @param player
	 * @return
	 */
	public ObservableList<Ship> getShips(boolean player) {
		ObservableList<Ship> ships = FXCollections.observableArrayList();
		for (Ship boat : theOne.mAllShipList) {
			if (boat.isPlayer() == player && boat.getDifficulty() == theOne.mDifficulty)
				ships.add(boat);
		}
		return ships;
	}

	/**
	 * This method get's all the ships from both teh AI and the player based on the Difficulty.
	 * This is hte overload method 
	 * @return
	 */
	public ObservableList<Ship> getShips() {
		ObservableList<Ship> ship = FXCollections.observableArrayList();

		for (Ship boat : theOne.mAllShipList) {
			if (boat.getDifficulty() == theOne.mDifficulty)
				ship.add(boat);
		}

		return ship;
	}

	/**
	 * This method returns the all Missiles observable list.
	 * @return
	 */
	public ObservableList<Missile> getAllMissiles() {
		return theOne.mAllMissileList;
	}

	/**
	 * This method returns all teh ships as an observable List.
	 * @return
	 */
	public ObservableList<Ship> getAllShips() {
		return theOne.mAllShipList;
	}

	/**
	 * This returns the highest scores for all three for a difficulty.
	 * @param difficulty
	 * @return
	 */
	public ObservableList<HighScore> getScores(String difficulty) {
		ObservableList<HighScore> scores = FXCollections.observableArrayList();
		for (HighScore hs : scores) {
			if (hs.getDifficulty().equals(difficulty))
				scores.add(hs);
		}
		return scores;
	}

	/**
	 * This return the current difficulty.
	 * @return
	 */
	public int getDifficulty() {
		return theOne.mDifficulty;
	}

	/**
	 * This will set the difficutly to another level.
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty) {
		theOne.mDifficulty = difficulty;
	}

	/**
	 * This returns the lucky Missile.
	 * @return
	 */
	public int getLuckyMissiles() {
		return theOne.mLuckyMissiles;
	}

	/**
	 * This sets how many lucky missiles a player has.
	 * @param luckyMissiles
	 */
	public void setLuckyMissiles(int luckyMissiles) {
		theOne.mLuckyMissiles = luckyMissiles;
	}

	/**
	 * This mehtod links up with the ConfirmationScnee.java and it willl check if there is already a game in progress.
	 * retursn a boolean.
	 * true means it already exisits false means there isn't a game.
	 * @return
	 */
	public boolean checkIfGameAlreadyExists() {

		try {
		switch (theOne.mDifficulty) {
		case 0:
			if (theOne.mEasyShipsDB.getRecordCount() > 0)
				return true;

		case 1:
			if (theOne.mNormShipsDB.getRecordCount() > 0)
				return true;

		case 2:
			if (theOne.mHardShipsDB.getRecordCount() > 0)
				return true;
		default:
			return false;
		}
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * This will call the checkIfGameAlreadyExists method and checks if it is a valid place.
	 * If that method returns true then there will be a ship added into the DB and into the ObservableList.
	 * @param col
	 * @param rol
	 * @param player
	 * @return
	 */
	public boolean validShipPlacement(char col, int rol, boolean player) {

		for (Ship boat : theOne.mAllShipList) {
			if (col == boat.getAphaCol() && rol == boat.getNumRol() && theOne.mDifficulty == boat.getDifficulty() && boat.isPlayer() == player)
				return false;
		}

		return true;
	}

	/**
	 * This adds a ship into the data Base.
	 * @param col
	 * @param row
	 * @param player
	 * @return
	 */
	public boolean addShip(char col, int row, boolean player) {

		if (theOne.validShipPlacement(col, row, player)) {
			String colStr = String.valueOf(col);
			String rolStr = String.valueOf(row);
			String playStr = player ? "1" : "0";
			String[] shipData = { colStr, rolStr, playStr, "0" };
			int iD;
			try {
				switch (theOne.mDifficulty) {
				case 0:
					iD = theOne.mEasyShipsDB
							.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
					break;
				case 1:
					iD = theOne.mNormShipsDB
							.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
					break;
				default:
					iD = theOne.mHardShipsDB
							.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
				}
				theOne.mAllShipList.add(new Ship(iD, col, row, player, theOne.mDifficulty, false));
				
				return true;
			} catch (SQLException e) {
				return false;
			}
		}
		
		return false;
		
	}

	/**
	 * This method is for hard mode for the game. 
	 * This method allows eht ai in hard mode. 
	 * @param boat
	 * @return
	 */
	public boolean removeShip(Ship boat) {

		try {
			switch (mDifficulty) {
			case 0:
				mEasyMissilesDB.deleteRecord(String.valueOf(boat.getId()));
				theOne.mAllShipList.remove(boat);
				return true;

			case 1:
				mNormShipsDB.deleteRecord(String.valueOf(boat.getId()));
				theOne.mAllShipList.remove(boat);
				return true;

			case 2:
				mHardShipsDB.deleteRecord(String.valueOf(boat.getId()));
				theOne.mAllShipList.remove(boat);
				return true;

			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	/**
	 * This method adds your score to the end when you finish the game.
	 * @param name
	 * @param lucky
	 * @param turns
	 * @return
	 */
	public boolean addScore(String name, int lucky, int turns) {
		try {
			String luckStr = String.valueOf(lucky);
			String turnStr = String.valueOf(turns);
			String[] scoreData = { name, turnStr, "", luckStr };
			switch (theOne.mDifficulty) {
			case 0:
				scoreData[2] = "Easy";
				break;
			case 1:
				scoreData[2] = "Normal";
				break;
			default:
				scoreData[2] = "Hard";
			}
			theOne.mHighScoreDB.createRecord(Arrays.copyOfRange(SCORE_FIELD_NAMES, 1, SCORE_FIELD_NAMES.length),
					scoreData);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * This checks if the place that the play wants to launch their missile is valid.
	 * if that place is valid then it will return a boolean.
	 * @param col
	 * @param row
	 * @param player
	 * @return
	 */
	public boolean isValideMissileLaunch(char col, int row, boolean player) {

		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.getAphaCol() == col && rocket.getNumRol() == row && rocket.getDifficulty() == theOne.mDifficulty
					&& rocket.isPlayer() == player)
				return false;
		}

		return true;
	}

	/**
	 * This addes a missile into the data base and the observable list.
	 * @param col
	 * @param row
	 * @param player
	 * @param lucky
	 * @return
	 */
	public boolean addMissile(char col, int row, boolean player, boolean lucky) {
		if(!theOne.isValideMissileLaunch(col, row, player))
			return false;
		
		String colStr = String.valueOf(col);
		String rolStr = String.valueOf(row);
		String playStr = player ? "1" : "0";
		String luckStr = lucky ? "1" : "0";
		String[] missileData = { colStr, rolStr, playStr, luckStr };
		int iD;
		try {
			switch (theOne.mDifficulty) {
			case 0:
				iD = theOne.mEasyMissilesDB.createRecord(
						Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
				break;
			case 1:
				iD = theOne.mNormMissilesDB.createRecord(
						Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
				break;
			default:
				iD = theOne.mHardMissilesDB.createRecord(
						Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
			}
			theOne.mAllMissileList.add(new Missile(iD, col, row, player, theOne.mDifficulty, lucky));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * This checks if the ships is destroyed or not.
	 * @param boat
	 * @return
	 */
	public boolean wreckShip(Ship boat) {
		if (!boat.isDestroy()) {
			try {
				String boatID = String.valueOf(boat.getId());
				String[] boatFields = { "down" };
				String[] boatValue = { "1" };
				boat.setIsDestroy(true);
				switch (theOne.mDifficulty) {
				case 0:
					return theOne.mEasyShipsDB.updateRecord(boatID, boatFields, boatValue);
				case 1:
					return theOne.mNormShipsDB.updateRecord(boatID, boatFields, boatValue);
				default:
					return theOne.mHardShipsDB.updateRecord(boatID, boatFields, boatValue);
				}
			} catch (SQLException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * This let's the ship move on hard mode.
	 * @param boat
	 * @return
	 */
	public boolean shipMove(Ship boat) {
		ObservableList<Ship> allyBoats = theOne.getShips(boat.isPlayer());
		int choice;
		int row = boat.getNumRol();
		int col = boat.getAphaCol();
		Random rNG = new Random();
		boolean[] obstructing = { boat.getNumRol() == 1, boat.getNumRol() == 10, boat.getAphaCol() == 'A',
				boat.getAphaCol() == 'J' };
		for (Ship ally : allyBoats) {
			if (ally.getAphaCol() == boat.getAphaCol() && ally.getNumRol() == boat.getNumRol() + 1)
				obstructing[1] = true;
			else if (ally.getAphaCol() == boat.getAphaCol() && ally.getNumRol() == boat.getNumRol() - 1)
				obstructing[0] = true;
			else if (ally.getAphaCol() == boat.getAphaCol() + 1 && ally.getNumRol() == boat.getNumRol())
				obstructing[3] = true;
			else if (ally.getAphaCol() == boat.getAphaCol() - 1 && ally.getNumRol() == boat.getNumRol())
				obstructing[2] = true;
		}
		if (obstructing[0] && obstructing[1] && obstructing[2] && obstructing[3])
			return false;
		do {
			choice = rNG.nextInt(4);
		} while (obstructing[choice]);

		switch (choice) {
		case 0:
			row--;
			break;
		case 1:
			row++;
			break;
		case 2:
			col--;
			break;
		case 3:
			col++;
			break;
		}
		String[] boatFields = { "col", "row" };
		String[] boatValues = { String.valueOf(Character.toChars(col)), String.valueOf(row) };
		try {
			switch (theOne.mDifficulty) {
			case 0:
				return theOne.mEasyShipsDB.updateRecord(String.valueOf(boat.getId()), boatFields, boatValues);
			case 1:
				return theOne.mNormShipsDB.updateRecord(String.valueOf(boat.getId()), boatFields, boatValues);
			default:
				return theOne.mHardShipsDB.updateRecord(String.valueOf(boat.getId()), boatFields, boatValues);
			}
		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * This method starts a new game and destroy the old game.
	 * @return
	 */
	public boolean startNewGame() {

		ObservableList<Ship> ship = theOne.getShips();
		ObservableList<Missile> miss = theOne.getMissilesLaunched();
		
		try {
			switch (theOne.mDifficulty) {
			case 0:
				mEasyShipsDB.deleteAllRecords();
				mEasyMissilesDB.deleteAllRecords();

				break;
			case 1:
				mNormShipsDB.deleteAllRecords();
				mNormMissilesDB.deleteAllRecords();
				break;
			default:
				mHardShipsDB.deleteAllRecords();
				mHardMissilesDB.deleteAllRecords();
				break;
			}

			for (Ship boat : ship) {
				theOne.mAllShipList.remove(boat);
			}
			for(Missile m: miss)
				theOne.mAllMissileList.remove(m);

			return true;

		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * This method retursn an ObservableList of all the ships that are still alive.
	 * @param player
	 * @return
	 */
	public ObservableList<Ship> getLivingShips(boolean player) {
		ObservableList<Ship> living = FXCollections.observableArrayList();

		for (Ship boat : theOne.getShips(player)) {
			if (theOne.wreckShip(boat))
				living.add(boat);
		}

		return living;

	}

}
