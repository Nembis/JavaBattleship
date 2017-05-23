package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import edu.orangecoastcollege.cs272.finalproject.model.Missile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

/**
 * This is the main game Scene.
 * You can choose where to attack and see where you ship is.
 * @author Dominic Mai
 *
 */
public class MainGameScene implements Initializable {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<Character> colCB;
	@FXML
	private ComboBox<Integer> rowCB;
	@FXML
	private Label numOfShips;
	@FXML
	private CheckBox useLuck;
	@FXML
	private GridPane aIBoard;
	@FXML
	private GridPane pRBoard;
	@FXML
	private ListView<Missile> missiles;

	private int shipCount = controller.getLivingShips(true).size();
	private int luckyCount = controller.getLuckyMissiles();

	/**
	 * This method loads in all the data befor ethe scene shows up.
	 */
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

		missiles.setItems(controller.getMissilesLaunched());
		
		numOfShips.setText(String.valueOf(shipCount));
		useLuck.setText("Luck: "+String.valueOf(luckyCount));
		useLuck.setDisable(luckyCount == 0);
		
		for(int x=0;x<10;x++)
			for(int y=0;y<10;y++)
			{
				pRBoard.add(ViewNavigator.generateSquare(true, x, y), x, y);
				aIBoard.add(ViewNavigator.generateSquare(false, x, y), x, y);
			}
	}

	/**
	 * this loads the difficulty Scene.
	 * @return
	 */
	@FXML
	public Object loadDifficultyScene() {
		ViewNavigator.loadScene("Difficulty Scene", ViewNavigator.DIFFICULTY_SCENE);

		return this;
	}

	/**
	 * This mehtod launches a missile.
	 * @return
	 */
	@FXML
	public Object launch() {

		if (controller.addMissile(colCB.getSelectionModel().getSelectedItem().charValue(), rowCB.getSelectionModel().getSelectedItem().intValue(),
				true, useLuck.isSelected()))
			ViewNavigator.loadScene("Missle Launch Scene", ViewNavigator.MISSILE_LAUNCH_SCENE);

		return this;
	}

}
