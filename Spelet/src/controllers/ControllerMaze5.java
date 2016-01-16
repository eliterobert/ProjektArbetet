package controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TreeMap;

import application.GameModel;
import application.MazeGame;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class ControllerMaze5 implements Initializable {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle player, finishLine;
	@FXML
	AnchorPane root;
	@FXML
	Label gameOverLabel, lifeLeftLabel;
	@FXML
	MenuItem restartMaze, startPage, quitGame, rules;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		startUp();
		GameModel.MAZEMODEL.getMain().getStage().setOnCloseRequest(e -> {
			GameModel.MAZEMODEL.collissionBoolean = false;
		});

		root.setOnKeyPressed(keyEvent -> {
			if (GameModel.MAZEMODEL.checkCollisionWithArrow(keyEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				StartPageController.currentPlayer.setPoints((int) GameModel.MAZEMODEL.lives);
				playerToHighScore();
				GameModel.MAZEMODEL.getMain().loadStartPage();
			}
		});

		player.setOnMouseDragged(mouseEvent -> {
			if (GameModel.MAZEMODEL.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.collissionBoolean = false;
				GameModel.MAZEMODEL.getMain().loadStartPage();
			}
		});
	}

	private void playerToHighScore() {
		DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
		Calendar cal = Calendar.getInstance();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File("src/Textfiles/WallOfFame.txt"), true))) {
			bw.append(StartPageController.currentPlayer.getPoints() + " Points Made by player : "
					+ StartPageController.currentPlayer.getName() + " in : " + dateFormat.format(cal.getTime()) + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	private void startUp() {
		GameModel.MAZEMODEL.collissionBoolean = true;
		GameModel.MAZEMODEL.setUpMenuItems(quitGame, restartMaze, startPage, rules, "Maze5");
		player.requestFocus();
		gameOverLabel.setVisible(false);
		lifeLeftLabel.setText("LIV KVAR: " + Integer.toString((int) GameModel.MAZEMODEL.lives));
	}

}
