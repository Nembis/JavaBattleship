package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DifficultyScene implements Initializable {

	//private static Controller controller = Controller.getInstance();
	
	@FXML
	private ComboBox<String> difficultyCB;
	
	@FXML
	private Button difficultyChooseBtn;
	
	@FXML
	public Object difficultyClick(){
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> difficulties = FXCollections.observableArrayList();
		difficulties.add("Easy");
		difficulties.add("Normal");
		difficulties.add("Hard");
		difficultyCB.setItems(difficulties);
		difficultyCB.getSelectionModel().select("Easy");
	}
}
