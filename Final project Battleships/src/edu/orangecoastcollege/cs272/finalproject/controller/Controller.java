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
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 1, lucky));
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
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 2, lucky));
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
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 3, lucky));
				}

				theOne.mEasyShipsDB = new DBModel(DB_NAME, SHIP_E_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mEasyShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 1, down));
				}

				theOne.mNormShipsDB = new DBModel(DB_NAME, SHIP_N_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mNormShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 2, down));
				}

				theOne.mHardShipsDB = new DBModel(DB_NAME, SHIP_H_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mHardShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(2));
					char col = rs.get(1).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 3, down));
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

	public ObservableList<Missile> getMissilesLaunched(boolean player) {
		ObservableList<Missile> missiles = FXCollections.observableArrayList();
		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.isPlayer() == player && rocket.getDifficulty() == theOne.mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	public ObservableList<Missile> getMissilesLaunched() {
		ObservableList<Missile> missiles = FXCollections.observableArrayList();
		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.getDifficulty() == theOne.mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	public ObservableList<Ship> getShips(boolean player) {
		ObservableList<Ship> ships = FXCollections.observableArrayList();
		for (Ship boat : theOne.mAllShipList) {
			if (boat.isPlayer() == player && boat.getDifficulty() == theOne.mDifficulty)
				ships.add(boat);
		}
		return ships;
	}

	public ObservableList<Ship> getShips() {
		ObservableList<Ship> ship = FXCollections.observableArrayList();

		for (Ship boat : theOne.mAllShipList) {
			if (boat.getDifficulty() == theOne.mDifficulty)
				ship.add(boat);

		}

		return ship;
	}

	public ObservableList<Missile> getAllMissiles() {
		return theOne.mAllMissileList;
	}

	public ObservableList<Ship> getAllShips() {
		return theOne.mAllShipList;
	}

	public ObservableList<HighScore> getScores(String difficulty) {
		ObservableList<HighScore> scores = FXCollections.observableArrayList();
		for (HighScore hs : scores) {
			if (hs.getDifficulty().equals(difficulty))
				scores.add(hs);
		}
		return scores;
	}

	public int getDifficulty() {
		return theOne.mDifficulty;
	}

	public void setDifficulty(int difficulty) {
		theOne.mDifficulty = difficulty;
	}

	public int getLuckyMissiles() {
		return theOne.mLuckyMissiles;
	}

	public void setLuckyMissiles(int luckyMissiles) {
		theOne.mLuckyMissiles = luckyMissiles;
	}

	public boolean checkIfGameAlreadyExists(int select) throws SQLException {

		switch (select) {
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
	}

	public boolean validShipPlacement(char col, int rol, boolean player) {

		for (Ship boat : theOne.mAllShipList) {
			if (col == boat.getAphaCol() && rol == boat.getNumRol() && theOne.mDifficulty == boat.getDifficulty() && boat.isPlayer() == player)
				return false;
		}

		return true;
	}

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

	public boolean isValideMissileLaunch(char col, int row, boolean player) {

		for (Missile rocket : theOne.mAllMissileList) {
			if (rocket.getAphaCol() == col && rocket.getNumRol() == row && rocket.getDifficulty() == theOne.mDifficulty
					&& rocket.isPlayer() == player)
				return false;
		}

		return true;
	}

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
		return true;
	}

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

	public boolean startNewGame() {

		ObservableList<Ship> ship = theOne.getShips();

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

			return true;

		} catch (SQLException e) {
			return false;
		}

	}

	public ObservableList<Ship> getLivingShips(boolean player) {
		ObservableList<Ship> living = FXCollections.observableArrayList();

		for (Ship boat : theOne.getShips(player)) {
			if (theOne.wreckShip(boat))
				living.add(boat);
		}

		return living;

	}

}
