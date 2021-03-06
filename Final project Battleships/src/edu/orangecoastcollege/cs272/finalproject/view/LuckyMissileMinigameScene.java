package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * This class loads the lucky missile mini game Scene
 * The lucky missile mini game scene allows you to win a missile that hits more then one place.
 * @author Dat Doan
 *
 */
public class LuckyMissileMinigameScene implements Initializable {

	private static Controller controller = Controller.getInstance();
	private Random rNGMissile = new Random();
	@FXML
	private Button choice1Btn;
	@FXML
	private Button choice2Btn;
	@FXML
	private Button choice3Btn;
	@FXML
	private Label choiceMessage;
	private int choice = -1;
	private int correctChoice = rNGMissile.nextInt(3);
	
	/**
	 * This method finds out which box the player choose and see if they win or not a lucky missile.
	 * @return
	 */
	@FXML
	public Object choice1()
	{
		choice = 0;
		choiceCheck();
		return this;
	}
	
	/**
	 * This is choose 2.
	 * @return
	 */
	@FXML
	public Object choice2()
	{
		choice = 1;
		choiceCheck();
		return this;
	}
	
	/**
	 * this is choose 3.
	 * @return
	 */
	@FXML
	public Object choice3()
	{
		choice = 2;
		choiceCheck();
		return this;
	}

	/**
	 * This method checks the choose.
	 */
	public void choiceCheck()
	{
		choice1Btn.setDisable(true);
		choice2Btn.setDisable(true);
		choice3Btn.setDisable(true);
		choiceMessage.setVisible(true);
		if(choice == correctChoice)
		{
			choiceMessage.setText("You found a Lucky Missile! Press the continue button.");
			controller.setLuckyMissiles(controller.getLuckyMissiles()+1);
		}
		else
			choiceMessage.setText("You found nothing. Press the continue button.");
	}
	
	/**
	 * this loads the next scene which is hte main game scene.
	 * @return
	 */
	@FXML
	public Object toNextScene()
	{
		ViewNavigator.loadScene("Missile Launch", ViewNavigator.MAIN_GAME_SCENE);
		return this;
	}
	
	/**
	 * This method loads the data before hte scene.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		choiceMessage.setVisible(false);
	}

}
