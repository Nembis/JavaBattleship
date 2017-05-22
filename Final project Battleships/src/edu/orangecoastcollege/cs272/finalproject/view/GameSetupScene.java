package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Ship;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class GameSetupScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<Character> mColCB;
	@FXML
	private ComboBox<Integer> mRolCB;
	@FXML
	private Label mNumOfShips;
	@FXML
	private Button mPlaceBtn;
	@FXML
	private Button mPlayBtn;
	@FXML
	private ListView<Ship> mShips;

	private int mCounterOfShips = 10;

	@FXML
	public Object PlaceShip() {

		boolean place = controller.addShip(mColCB.getSelectionModel().getSelectedItem(),
				mRolCB.getSelectionModel().getSelectedIndex(), true);

		if (place) {
			mCounterOfShips--;
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}
		
		if(mCounterOfShips == 0)
			mPlayBtn.setDisable(false);
		else
			mPlayBtn.setDisable(true);
			
		return this;
	}

	@FXML
	public Object remove(){

		boolean remove = controller.removeShip(mShips.getSelectionModel().getSelectedItem());
		
		if(remove){
			mCounterOfShips++;
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}
		
		if(mCounterOfShips == 0)
			mPlayBtn.setDisable(false);
		else
			mPlayBtn.setDisable(true);
		
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Character> letters = FXCollections.observableArrayList();
		/*
		letters.add('A');
		letters.add('B');
		letters.add('C');
		letters.add('D');
		letters.add('E');
		letters.add('F');
		letters.add('G');
		letters.add('H');
		letters.add('I');
		letters.add('j');
		*/
		String lettersBase = "ABCDEFGHIJ";
		for(int c=0;c<lettersBase.length();c++)
			letters.add(lettersBase.charAt(c));

		mColCB.setItems(letters);
		mColCB.getSelectionModel().select('A');

		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(i);
		}
		mRolCB.setItems(numbers);
		mRolCB.getSelectionModel().select(1);

		mNumOfShips.setText("10");
		
		mPlayBtn.setDisable(true);
	}
	
	@FXML
	public Object loadMainGameScene(){
		
		ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);
		
		return this;
	}

}
