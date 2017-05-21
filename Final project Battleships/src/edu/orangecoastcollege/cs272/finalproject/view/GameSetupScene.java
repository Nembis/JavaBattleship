package edu.orangecoastcollege.cs272.finalproject.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class GameSetupScene implements Initializable{

	@FXML
	private ComboBox<Character> mCol;
	@FXML
	private ComboBox<Integer> mRol;
	@FXML
	private Label mNumOfShips;
	@FXML
	private Button mPlaceBtn;
	@FXML
	private Button mPlay;
	
	@Override
	public Object initialize(){
		
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
		
		mCol.setItems(letters);
		
		ObservableList<Integer> numbers = FXCollections.observableArrayList();
		for(int i = i; i < 11; i++){
			numbers.add(i);
		}
		mRol.setItems(numbers);
		
		mNumOfShips.setText("10");
		
		mPlay.setDisable(true);
		
		return this;
	}
	
}
