package edu.orangecoastcollege.cs272.finalproject.view;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DifficultyScene {

	private static Controller controller = Controller.getInstance();

	@FXML
	private ComboBox<String> difficultyCB;

	@FXML
	private Button difficultyChooseBtn;

	@FXML
	public Object initialize() {
		difficultyCB.setItems(controller.getDifficulty());

		return this;
	}

	@FXML
	public Object difficultyClick() {
		if (controller.checkIfGameAlreadyExsists(difficultyCB.getItems()) && difficultyCB.getItems() != null) {
			ViewNavigator.loadScene("Confirmation Scene", ViewNavigator.CONFIRMATION_SCENE);
			return this;
		}

		ViewNavigator.loadScene("", ViewNavigator.DIFFICULTY_REVIEW_SCENE);

		return this;
	}
}
