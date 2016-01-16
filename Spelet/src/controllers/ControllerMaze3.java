package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GameModel;
import application.MazeGame;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ControllerMaze3 implements Initializable, ControllerInterface {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle player, finishLine, enemy1, enemy2, enemy3, enemy4, enemy5, enemy6;
	@FXML
	AnchorPane root;
	@FXML
	Label gameOverLabel, lifeLeftLabel;
	@FXML
	MenuItem restartMaze, startPage, quitGame, rules;

	Thread thread;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		startUp();

		GameModel.MAZEMODEL.getMain().getStage().setOnCloseRequest(e -> {
			GameModel.MAZEMODEL.collissionBoolean = false;
		});

		root.setOnKeyPressed(keyEvent -> {
			enemyCollision();
			if (GameModel.MAZEMODEL.checkCollisionWithArrow(keyEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.collissionBoolean = false;
				GameModel.MAZEMODEL.getMain().loadMaze("Maze4");

			}

		});
		player.setOnMouseDragged(mouseEvent -> {
			if (GameModel.MAZEMODEL.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.collissionBoolean = false;
				GameModel.MAZEMODEL.getMain().loadMaze("Maze4");
			}
		});
	}

	@Override
	public void startUp() {
		GameModel.MAZEMODEL.collissionBoolean = true;
		player.requestFocus();
		gameOverLabel.setVisible(false);
		lifeLeftLabel.setText("LIV KVAR: " + Integer.toString((int) GameModel.MAZEMODEL.lives));
		GameModel.MAZEMODEL.setUpMenuItems(quitGame, restartMaze, startPage, rules, "Maze3");
		startEnemyMotions();
		runCollision();

	}

	public void startEnemyMotions() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(2700), enemy1);
		tt.setByX(145);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3000), enemy2);
		tt.setByY(105);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(700), enemy3);
		tt.setByY(-90);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy4);
		tt.setByX(-95);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy5);
		tt.setByY(-250);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy6);
		tt.setByY(250);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
	}

	@Override
	public void enemyCollision() {
		if (GameModel.MAZEMODEL.collisionWithEnemy(player, enemy1, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy2, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy3, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy4, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy5, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy6, gameOverLabel, lifeLeftLabel) == true) {
			GameModel.MAZEMODEL.collissionBoolean = false;
		} else
			GameModel.MAZEMODEL.collissionBoolean = true;
	}

	@Override
	public void runCollision() {
		thread = new Thread(() -> {
			while (GameModel.MAZEMODEL.collissionBoolean) {
				enemyCollision();
				try {
					Thread.sleep(233);
				} catch (Exception e) {
					System.out.println("Thread.sleep interupted");
				}
			}
		});

		thread.start();
	}
}
