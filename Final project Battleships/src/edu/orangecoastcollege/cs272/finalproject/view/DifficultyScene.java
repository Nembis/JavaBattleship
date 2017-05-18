package edu.orangecoastcollege.cs272.finalproject.view;

//import edu.orangecoastcollege.cs272.finalproject.controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DifficultyScene {

	//private static Controller controller = Controller.getInstance();
	
	@FXML
	private ComboBox<String> difficultyCB;
	
	@FXML
	private Button difficultyChooseBtn;
	
	@FXML
	public Object difficultyClick(){
		return null;
	}
}
