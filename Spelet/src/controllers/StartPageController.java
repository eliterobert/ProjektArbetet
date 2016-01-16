package controllers;

import application.StartPageModel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.ResourceBundle;

import application.MazeGame;
import application.Player;
import application.StartPageModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

/**
 * Controller for start page, sets actionevents.
 * 
 * @author Robert E
 * @version 1.0
 */
public class StartPageController implements Initializable {

	@FXML
	Button startButton, ruleButton, exitButton, createPlayerButton;
	@FXML
	TextField playerNameTextField;
	@FXML
	Label nameBackgoundLabel;
	@FXML
	TextArea highScoreArea;
	@FXML
	ComboBox<String> recentPlayerBox;

	/**
	 * Static player needs to be used over the game without creating a new
	 * player every single time need a reference.
	 */
	public static Player currentPlayer;
	private LinkedList<String> scores;
	private StartPageModel startPageModel;
	private MazeGame main;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startPageModel = new StartPageModel();
		scores = new LinkedList<>();
		main = new MazeGame();
		setButtonActions();
		startUp();
	}

	private void setButtonActions() {

		recentPlayerBox.setOnAction(e -> {
			playerNameTextField.setText(recentPlayerBox.getSelectionModel().getSelectedItem());
			playerNameTextField.setEditable(false);
		});

		startButton.setOnAction(e -> {
			if (!currentPlayer.getName().equals("")) {
				main.loadMaze("Maze1");
			} else {
				showAlert();
			}

		});

		createPlayerButton.setOnAction(e -> {
			if (playerNameTextField.getText().equals("")) {
				showAlert();
			} else {
				currentPlayer = new Player(0, playerNameTextField.getText());
				startPageModel.playerToRecentPlayers(currentPlayer);
				recentPlayerBox.getItems().add(currentPlayer.getName());
				playerNameTextField.setEditable(false);
				nameBackgoundLabel.setText(currentPlayer.getName());
				nameBackgoundLabel.setVisible(true);
				startButton.setDisable(false);
			}

		});

		exitButton.setOnAction(e -> {
			Platform.exit();
		});

		ruleButton.setOnAction(e -> {
			main.loadRules();
		});

	}

	private void showAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Please enter playername");
		alert.showAndWait();
	}

	private void startUp() {
		Tooltip startTip = new Tooltip("Enter a name and add it to the list to continue!");
		createPlayerButton.setTooltip(startTip);
		startButton.setDisable(true);
		startPageModel.readRecentPlayersToComboBox(recentPlayerBox);
		highScoreArea.setEditable(false);
		highScoreArea.setMouseTransparent(true);
		nameBackgoundLabel.setVisible(false);
		startPageModel.loadHighScore(scores, highScoreArea);
	}

}
