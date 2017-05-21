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
	private Button mPlay;
	@FXML
	private ListView<Ship> mShips;

	private int mCounterOfShips = 10;

	@FXML
	public Object PlaceShip() {

		boolean place = controller.addShip(mColCB.getSelectionModel().getSelectedItem(),
				mRolCB.getSelectionModel().getSelectedIndex(), true);

		if (place) {
			mCounterOfShips--;
			mShips.setItems(controller.getShips(true));
		}

		return this;
	}

	@FXML
	public Object remove(){

		controller.removeShip(mShips.getSelectionModel().getSelectedItem());
		
		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Character> letters = FXCollections.observableArrayList();
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

		mColCB.setItems(letters);
		mColCB.getSelectionModel().select('A');

		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(i);
		}
		mRolCB.setItems(numbers);
		mRolCB.getSelectionModel().select(1);

		mNumOfShips.setText("10");

		mPlay.setDisable(true);

	}

}
