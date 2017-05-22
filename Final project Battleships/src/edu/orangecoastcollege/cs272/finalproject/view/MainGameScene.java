package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.View;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MainGameScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<Character> colCB;
	@FXML
	private ComboBox<Integer> rowCB;
	@FXML
	private Label numOfShips;
	@FXML
	private Label numOfLucky;
	@FXML
	private CheckBox useLuck;

	private int shipCount = controller.getLivingShips().size();
	private int luckyCount = controller.getLuckyMissiles();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Character> letters = FXCollections.observableArrayList();

		String lettersBase = "ABCDEFGHIJ";
		for (int c = 0; c < lettersBase.length(); c++)
			letters.add(lettersBase.charAt(c));

		colCB.setItems(letters);
		colCB.getSelectionModel().select('A');

		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(i);
		}
		rowCB.setItems(numbers);
		rowCB.getSelectionModel().select(1);

		numOfShips.setText(String.valueOf(shipCount));
		numOfLucky.setText(String.valueOf(luckyCount));

	}

	@FXML
	public Object loadDifficultyScene() {
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);

		return this;
	}

	@FXML
	public Object launch() {

		controller.addMissile(colCB.getSelectionModel().getSelectedItem(), rowCB.getSelectionModel().getSelectedItem(),
				true, useLuck.isSelected());
		
		ViewNavigator.loadScene("Missle Launch Scene", ViewNavigator.MISSILE_LAUNCH_SCENE);

		return this;
	}

}
