package edu.orangecoastcollege.cs272.finalproject.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;

public class TestController {
	
	private static Controller theOne;

	@BeforeClass
	public static void setUpBeforeClass(){
		theOne = Controller.getInstance();
		for(int d=0;d<=2;d++)
		{
			theOne.setDifficulty(d);
			theOne.startNewGame();
		}
		theOne.setDifficulty(0);
	}
	
	@Before
	public void setUp() throws Exception {
		theOne = Controller.getInstance();
		theOne.setDifficulty(0);
	}

	@After
	public void tearDown() throws Exception {
		for(int d=0;d<=2;d++)
		{
			theOne.setDifficulty(d);
			theOne.startNewGame();
		}
	}

	@Test
	public void testGetInstance() {
		assertTrue("Method had an SQLException",Controller.getInstance() != null);
	}

	@Test
	public void testGetMissilesLaunchedBoolean() {
		theOne.addMissile('A', 1, true, false,true);
		theOne.addMissile('A', 2, true, false,true);
		theOne.addMissile('A', 1, false, false,true);
		assertEquals("All or no missiles are found for player.",2,theOne.getMissilesLaunched(true).size());
		assertEquals("All or no missiles are found for AI.",1,theOne.getMissilesLaunched(false).size());
	}

	@Test
	public void testGetMissilesLaunched() {
		theOne.addMissile('A', 1, true,false,true);
		assertEquals("Missile was not found normally",1,theOne.getMissilesLaunched().size());
		theOne.addMissile('A', 1, false,false,true);
		assertEquals("All missiles of one difficulty was not found",2,theOne.getMissilesLaunched().size());
		theOne.setDifficulty(2);
		theOne.addMissile('A', 1, true,false,true);
		assertEquals("Recieved missiles form different difficulties",1,theOne.getMissilesLaunched().size());
	}

	@Test
	public void testGetShipsBoolean() {
		theOne.addShip('A', 1, true);
		theOne.addShip('A', 2, true);
		theOne.addShip('A', 1, false);
		assertEquals("All or no ships are found for player.",2,theOne.getShips(true).size());
		assertEquals("All or no ships are found for AI.",1,theOne.getShips(false).size());
	}

	@Test
	public void testGetShips() {
		theOne.addShip('A', 1, true);
		assertEquals("Ship was not found normally",1,theOne.getShips().size());
		theOne.addShip('A', 1, false);
		assertEquals("All ships of one difficulty was not found",2,theOne.getShips().size());
		theOne.setDifficulty(1);
		theOne.addShip('A', 1, true);
		assertEquals("Recieved ships form different difficulties",1,theOne.getShips().size());
	}

	@Test
	public void testGetAllMissiles() {
		theOne.addMissile('A', 1, true, false,true);
		theOne.setDifficulty(2);
		theOne.addMissile('A', 1, true, false,true);
		assertEquals("All missiles were not found",2,theOne.getAllMissiles().size());
	}

	@Test
	public void testGetAllShips() {
		theOne.addShip('A', 1, true);
		theOne.setDifficulty(2);
		theOne.addShip('A', 1, true);
		assertEquals("All ships were not found",2,theOne.getAllShips().size());
	}

	@Test
	public void testAddScore() {
		theOne.clearScores();
		assertTrue("SQLException thrown when adding score",theOne.addScore("bob", 0, 15));
	}
	
	@Test
	public void testGetScores() {
		theOne.clearScores();
		theOne.addScore("bob", 0, 15);
		assertEquals("Score cannot be found",1,theOne.getScores("Easy").size());
	}

	@Test
	public void testCheckIfGameAlreadyExists() {
		theOne.addShip('A', 1, true);
		assertTrue("SQLException was thrown when trying to check data",theOne.checkIfGameAlreadyExists());
	}

	@Test
	public void testValidShipPlacement() {
		assertTrue("Controller was not cleaned out properly",theOne.validShipPlacement('A', 1, true));
		theOne.addShip('A', 1, true);
		assertFalse("Placed ship was unable to be found",theOne.validShipPlacement('A', 1, true));
	}

	@Test
	public void testAddShip() {
		assertTrue("SQLException thrown attempting to add a ship.",theOne.addShip('A', 1, true));
		assertEquals(1,theOne.getShips().size());
	}

	@Test
	public void testRemoveShip() {
		theOne.addShip('A', 1, true);
		assertEquals("Ship was not sucessfully added to ObservableList.",1,theOne.getShips(true).size());
		assertTrue("SQLException thrown attempting to remove a ship.",theOne.removeShip(theOne.getShips(true).get(0)));
	}

	@Test
	public void testIsValideMissileLaunch() {
		assertTrue("Controller is not cleaned properly",theOne.isValideMissileLaunch('A', 1, true));
		theOne.addMissile('A', 1, true,false,true);
		assertFalse("Unable to find missile",theOne.isValideMissileLaunch('A', 1, true));
	}

	@Test
	public void testAddMissile() {
		assertEquals("Controller is not cleaned",0,theOne.getAllMissiles().size());
		assertTrue("SQLException when adding missile",theOne.addMissile('A', 1, true, false,true));
		assertEquals("SQLException when adding missile",1,theOne.getAllMissiles().size());
	}

	@Test
	public void testWreckShip() {
		theOne.addShip('A', 1, true);
		assertTrue("SQLException is thrown when attempting to update",theOne.wreckShip(theOne.getShips().get(0)));
	}

	@Test
	public void testShipMove() {
		assertTrue("SQLException thrown when updating ship",theOne.addShip('C', 5, false));
	}

	@Test
	public void testStartNewGame() {
		theOne.addShip('A', 1, true);
		assertTrue("SQLException thrown when clearing database",theOne.startNewGame());
	}

	@Test
	public void testGetLivingShips() {
		theOne.addShip('A', 1, true);
		assertEquals("Ship was not found",1,theOne.getLivingShips(true).size());
		theOne.wreckShip(theOne.getShips().get(0));
		assertEquals("SQLException thrown when attempting to update",0,theOne.getLivingShips(true).size());
	}

}
