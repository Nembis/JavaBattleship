package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.Random;
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
	public Object placeShip() {

		boolean place = controller.addShip(colCB.getSelectionModel().getSelectedItem().charValue(),
				rowCB.getSelectionModel().getSelectedItem().intValue(), true);

		if (place) {
			mCounterOfShips--;
			mNumOfShips.setText(String.valueOf(mCounterOfShips));
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}

		mPlayBtn.setDisable(mCounterOfShips != 0);
		mPlaceBtn.setDisable(mCounterOfShips == 0);
		mRemoveBtn.setDisable(mCounterOfShips == 10);

		mBoard.getChildren().clear();
		for(int x=0;x<10;x++)
			for(int y=0;y<10;y++)
				mBoard.add(ViewNavigator.generateSquare(true, x, y), x, y);
		return this;
	}

	@FXML
	public Object remove() {

		boolean remove = controller.removeShip(mShips.getSelectionModel().getSelectedItem());

		if (remove) {
			mCounterOfShips++;
			mNumOfShips.setText(String.valueOf(mCounterOfShips));
			mShips.setAccessibleText(String.valueOf(mCounterOfShips));
			mShips.setItems(controller.getShips(true));
		}

		mPlayBtn.setDisable(mCounterOfShips != 0);
		mPlaceBtn.setDisable(mCounterOfShips == 0);
		mRemoveBtn.setDisable(mCounterOfShips == 10);

		mBoard.getChildren().clear();
		for(int x=0;x<10;x++)
			for(int y=0;y<10;y++)
				mBoard.add(ViewNavigator.generateSquare(true, x, y), x, y);

		return this;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		ObservableList<Character> letters = FXCollections.observableArrayList();

		String lettersBase = "ABCDEFGHIJ";
		for (int c = 0; c < lettersBase.length(); c++)
			letters.add(lettersBase.charAt(c));

		colCB.setItems(letters);
		colCB.getSelectionModel().select(0);


		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for (int i = 1; i < 11; i++) {
			numbers.add(i);
		}
		rowCB.setItems(numbers);
		rowCB.getSelectionModel().select(0);
		
		mShips.setItems(controller.getShips(true));


		mNumOfShips.setText("10");


		mNumOfShips.setText(String.valueOf(10 - controller.getShips(true).size()));
		

		mPlayBtn.setDisable(true);
		mRemoveBtn.setDisable(true);

		
		for(int x=0;x<10;x++)
			for(int y=0;y<10;y++)
				mBoard.add(ViewNavigator.generateSquare(true, x, y), x, y);
	}

	@FXML
	public Object loadMainGameScene(){
		
		Random rNG = new Random();
		int x=0,y=0;
		for(int s=0;s<10;s++)
		{
			do{
				x = rNG.nextInt(10);
				y = rNG.nextInt(10);
			}while(!controller.validShipPlacement(Character.toChars(Character.getNumericValue('A')+x)[0], y+1, false));
			controller.addShip(Character.toChars(Character.getNumericValue('A')+x)[0], y+1, false);
		}
		
		ViewNavigator.loadScene("Main Game Scene", ViewNavigator.MAIN_GAME_SCENE);

		return this;
	}

}
