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
import javafx.scene.layout.GridPane;

public class GameSetupScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<Character> colCB;
	@FXML
	private ComboBox<Integer> rowCB;
	@FXML
	private Label mNumOfShips;
	@FXML
	private Button mPlaceBtn;
	@FXML
	private Button mPlayBtn;
	@FXML
	private Button mRemoveBtn;
	@FXML
	private ListView<Ship> mShips;
	@FXML
	private GridPane mBoard;

	private int mCounterOfShips = 10;

	@FXML
	public Object PlaceShip() {

		boolean place = controller.addShip(colCB.getSelectionModel().getSelectedItem(),
				rowCB.getSelectionModel().getSelectedItem(), true);

		if (place) {
			mCounterOfShips--;
			mNumOfShips.setText(String.valueOf(mCounterOfShips));
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}

		mPlayBtn.setDisable(mCounterOfShips != 0);
		mPlaceBtn.setDisable(mCounterOfShips == 0);
		mRemoveBtn.setDisable(mCounterOfShips == 10);
		
		mBoard = ViewNavigator.generateBoard(true);
		return this;
	}

	@FXML
	public Object remove(){

		boolean remove = controller.removeShip(mShips.getSelectionModel().getSelectedItem());
		
		if(remove){
			mCounterOfShips++;
			mNumOfShips.setText(String.valueOf(mCounterOfShips));
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}
		
		mPlayBtn.setDisable(mCounterOfShips != 0);
		mPlaceBtn.setDisable(mCounterOfShips == 0);
		mRemoveBtn.setDisable(mCounterOfShips == 10);
		
		mBoard = ViewNavigator.generateBoard(true);
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Character> letters = FXCollections.observableArrayList();
		
		String lettersBase = "ABCDEFGHIJ";
		for(int c = 0; c < lettersBase.length(); c++)
			letters.add(lettersBase.charAt(c));

		colCB.setItems(letters);
		colCB.getSelectionModel().select(0);;

		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(i);
		}
		rowCB.setItems(numbers);
		rowCB.getSelectionModel().select(0);

		mNumOfShips.setText("10");
		
		mPlayBtn.setDisable(true);
		mRemoveBtn.setDisable(true);
		
		mBoard = ViewNavigator.generateBoard(true);
	}
	
	@FXML
	public Object loadMainGameScene(){
		
		ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);
		
		return this;
	}

}
