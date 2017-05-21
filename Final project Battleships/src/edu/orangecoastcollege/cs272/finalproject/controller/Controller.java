package edu.orangecoastcollege.cs272.finalproject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import edu.orangecoastcollege.cs272.finalproject.model.DBModel;
import edu.orangecoastcollege.cs272.finalproject.model.HighScore;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

	private static Controller theOne;

	private static final String DB_NAME = "game_data.db";

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
	private static final String[] SCORE_FIELD_NAMES = {"id","name","turns","difficulty","luckys"};
	private static final String[] SCORE_FIELD_TYPES = {"INTEGER PRIMARY KEY","TEXT","INTEGER","TEXT","INTEGER"};

	private int mDifficulty;
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
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 1, lucky));
				}

				theOne.mNormMissilesDB = new DBModel(DB_NAME, MISSILE_N_TABLE_NAME, MISSILE_FIELD_NAMES,
						MISSILE_FIELD_TYPES);
				records = theOne.mNormMissilesDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 2, lucky));
				}

				theOne.mHardMissilesDB = new DBModel(DB_NAME, MISSILE_H_TABLE_NAME, MISSILE_FIELD_NAMES,
						MISSILE_FIELD_TYPES);
				records = theOne.mHardMissilesDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 3, lucky));
				}

				theOne.mEasyShipsDB = new DBModel(DB_NAME, SHIP_E_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mEasyShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 1, down));
				}

				theOne.mNormShipsDB = new DBModel(DB_NAME, SHIP_N_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mNormShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 2, down));
				}

				theOne.mHardShipsDB = new DBModel(DB_NAME, SHIP_H_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mHardShipsDB.getAllRecords();
				for (ArrayList<String> rs : records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					char col = rs.get(2).charAt(0);
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
			if (rocket.isPlayer() == player && rocket.getDifficulty() == mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	public ObservableList<Ship> getShips(boolean player) {
		ObservableList<Ship> ships = FXCollections.observableArrayList();
		for (Ship boat : theOne.mAllShipList) {
			if (boat.isPlayer() == player && boat.getDifficulty() == mDifficulty)
				ships.add(boat);
		}
		return ships;
	}

	public ObservableList<Missile> getAllMissiles() {
		return theOne.mAllMissileList;
	}

	public ObservableList<Ship> getAllShips() {
		return theOne.mAllShipList;
	}

	public ObservableList<HighScore> getScores(String difficulty)
	{
		ObservableList<HighScore> scores = FXCollections.observableArrayList();
		for(HighScore hs: scores)
		{
			if(hs.getDifficulty().equals(difficulty))
				scores.add(hs);
		}
		return scores;
	}
	
	public int getDifficulty() {
		return mDifficulty;
	}

	public void setDifficulty(int difficulty) {
		mDifficulty = difficulty;
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

	public boolean validShipPlacement(char col, int rol) {

		for(Ship boat : theOne.mAllShipList){
			if(col == boat.getAphaCol() && rol == boat.getNumRol() && theOne.mDifficulty == boat.getDifficulty())
				return false;
		}
		
		return true;
	}
	
	public boolean addShip(char col, int row, boolean player){
		
		if(theOne.validShipPlacement(col, row)){
			String colStr = String.valueOf(col);
			String rolStr = String.valueOf(row);
			String playStr = player?"1":"0";
			String[] shipData = {colStr, rolStr, playStr, "0"};
			int iD;
			try {
			switch(theOne.mDifficulty){
			case 0:
				iD = theOne.mEasyShipsDB.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
				break;
			case 1:
				iD = theOne.mNormShipsDB.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
				break;
			default:
				iD = theOne.mHardShipsDB.createRecord(Arrays.copyOfRange(SHIP_FIELD_NAMES, 1, SHIP_FIELD_NAMES.length), shipData);
			}
			theOne.mAllShipList.add(new Ship(iD, col, row, player, theOne.mDifficulty, false));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	public boolean addMissile(char col, int row, boolean player, boolean lucky)
	{
		String colStr = String.valueOf(col);
		String rolStr = String.valueOf(row);
		String playStr = player?"1":"0";
		String luckStr = lucky?"1":"0";
		String[] missileData = {colStr, rolStr, playStr, luckStr};
		int iD;
		try{
			switch(theOne.mDifficulty)
			{
			case 0:
				iD = theOne.mEasyMissilesDB.createRecord(Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
				break;
			case 1:
				iD = theOne.mNormMissilesDB.createRecord(Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
				break;
			default:
				iD = theOne.mHardMissilesDB.createRecord(Arrays.copyOfRange(MISSILE_FIELD_NAMES, 1, MISSILE_FIELD_NAMES.length), missileData);
			}
			theOne.mAllMissileList.add(new Missile(iD, col, row, player, theOne.mDifficulty, lucky));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}

}
