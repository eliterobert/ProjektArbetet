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

public class ControllerMaze2 implements Initializable, ControllerInterface {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle player, finishLine, enemy1, enemy2, enemy3, enemy4, enemy5;
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
			GameModel.MAZEMODEL.threadLoop = false;
		});

		root.setOnKeyPressed(keyEvent -> {
			if (GameModel.MAZEMODEL.keyEventHandling(keyEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.getMain().loadMaze("Maze3");
				GameModel.MAZEMODEL.threadLoop = false;
			}
		});
		player.setOnMouseDragged(mouseEvent -> {
			if (GameModel.MAZEMODEL.mouseEventHandling(mouseEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.threadLoop = false;
				GameModel.MAZEMODEL.getMain().loadMaze("Maze3");
			}
		});
	}

	@Override
	public void startUp() {
		GameModel.MAZEMODEL.threadLoop = true;
		GameModel.MAZEMODEL.setUpMenuItems(quitGame, restartMaze, startPage, rules, "Maze2");
		player.requestFocus();
		gameOverLabel.setVisible(false);
		lifeLeftLabel.setText("LIFE LEFT: " + Integer.toString((int) GameModel.MAZEMODEL.lives));
		startEnemyMotions();
		runCollision();

	}

	@Override
	public void startEnemyMotions() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(5000), enemy1);
		tt.setByY(200);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3000), enemy2);
		tt.setByX(235);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy3);
		tt.setByX(-400);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy4);
		tt.setByX(-150);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(2000), enemy5);
		tt.setByY(-400);
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
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy5, gameOverLabel, lifeLeftLabel) == true) {
			GameModel.MAZEMODEL.threadLoop = false;
		} else
			GameModel.MAZEMODEL.threadLoop = true;
	}

	@Override
	public void runCollision() {
		thread = new Thread(() -> {
			while (GameModel.MAZEMODEL.threadLoop) {
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
