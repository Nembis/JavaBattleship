package edu.orangecoastcollege.cs272.finalproject.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import edu.orangecoastcollege.cs272.finalproject.model.DBModel;
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
	private static final String[] MISSILE_FIELD_NAMES = {"id", "col", "row", "player_owned","lucky"};
	private static final String[] MISSILE_FIELD_TYPES = {"INTEGER PRIMARY KEY","TEXT","INTEGER","INTEGER","INTEGER"};

	private static final String SHIP_E_TABLE_NAME = "easy_ships";
	private static final String SHIP_N_TABLE_NAME = "norm_ships";
	private static final String SHIP_H_TABLE_NAME = "hard_ships";
	private static final String[] SHIP_FIELD_NAMES = {"id", "col", "row", "player_owned","down"};
	private static final String[] SHIP_FIELD_TYPES = {"INTEGER PRIMARY KEY","TEXT","INTEGER","INTEGER","INTEGER"};

	private int mDifficulty;
	private DBModel mEasyShipsDB;
	private DBModel mEasyMissilesDB;
	private DBModel mNormShipsDB;
	private DBModel mNormMissilesDB;
	private DBModel mHardShipsDB;
	private DBModel mHardMissilesDB;
	//private DBModel mHighScoreDB;

	private ObservableList<Missile> mAllMissileList;
	private ObservableList<Ship> mAllShipList;

	private Controller() {
	}

	public static Controller getInstance() {
		if (theOne == null) {
			theOne = new Controller();
			theOne.mAllMissileList = FXCollections.observableArrayList();
			theOne.mAllShipList = FXCollections.observableArrayList();

			try {
				theOne.mEasyMissilesDB = new DBModel(DB_NAME, MISSILE_E_TABLE_NAME, MISSILE_FIELD_NAMES, MISSILE_FIELD_TYPES);
				ArrayList<ArrayList<String>> records = theOne.mEasyMissilesDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 1, lucky));
				}

				theOne.mNormMissilesDB = new DBModel(DB_NAME, MISSILE_N_TABLE_NAME, MISSILE_FIELD_NAMES, MISSILE_FIELD_TYPES);
				records = theOne.mNormMissilesDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 2, lucky));
				}

				theOne.mHardMissilesDB = new DBModel(DB_NAME, MISSILE_H_TABLE_NAME, MISSILE_FIELD_NAMES, MISSILE_FIELD_TYPES);
				records = theOne.mHardMissilesDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean lucky = rs.get(4).equals("1");
					theOne.mAllMissileList.add(new Missile(id, col, row, player, 3, lucky));
				}

				theOne.mEasyShipsDB = new DBModel(DB_NAME, SHIP_E_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mEasyShipsDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 1, down));
				}

				theOne.mNormShipsDB = new DBModel(DB_NAME, SHIP_N_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mNormShipsDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 2, down));
				}

				theOne.mHardShipsDB = new DBModel(DB_NAME, SHIP_H_TABLE_NAME, SHIP_FIELD_NAMES, SHIP_FIELD_TYPES);
				records = theOne.mHardShipsDB.getAllRecords();
				for(ArrayList<String> rs: records) {
					int id = Integer.parseInt(rs.get(0));
					int row = Integer.parseInt(rs.get(1));
					String col = rs.get(2);
					boolean player = rs.get(3).equals("1");
					boolean down = rs.get(4).equals("1");
					theOne.mAllShipList.add(new Ship(id, col, row, player, 3, down));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return theOne;
	}

	public ObservableList<Missile> getMissilesLaunched(boolean player)
	{
		ObservableList<Missile> missiles = FXCollections.observableArrayList();
		for(Missile rocket : theOne.mAllMissileList)
		{
			if(rocket.isPlayer() == player && rocket.getDifficulty()== mDifficulty)
				missiles.add(rocket);
		}
		return missiles;
	}

	public ObservableList<Ship> getShips(boolean player)
	{
		ObservableList<Ship> ships = FXCollections.observableArrayList();
		for(Ship boat : theOne.mAllShipList)
		{
			if(boat.isPlayer() == player && boat.getDifficulty()== mDifficulty)
				ships.add(boat);
		}
		return ships;
	}

	public ObservableList<Missile> getAllMissiles()
	{
		return theOne.mAllMissileList;
	}

	public ObservableList<Ship> getAllShips() {
		return theOne.mAllShipList;
	}

	public ObservableList<String> getDifficulty(){
		ObservableList<String> difficulty = FXCollections.observableArrayList();
		difficulty.add("Easy");
		difficulty.add("Normal");
		difficulty.add("Hard");

		return difficulty;
	}
}
