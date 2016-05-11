package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GameModel;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

/**
 * Controller class, implements Initializable and controllerinterface. Uses
 * methods from GameModel singleton. Controls all buttons and events. Uses a own
 * thread to the enemyCollision() method.
 * 
 * @author Robert E
 * @version 1.0
 */

public class ControllerMaze1 implements Initializable, ControllerInterface {

	@FXML
	SVGPath map;
	@FXML
	Rectangle player, finishLine, enemy1, enemy2, enemy3;
	@FXML
	AnchorPane root;
	@FXML
	Label gameOverLabel, lifeLeftLabel;
	@FXML
	MenuItem restartMaze, startPage, quitGame, rules;

	private Thread thread;

	/**
	 * Start up maze with method, sets keyevent and mouse actions
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		startUp();

		// Om programmet stängs ner med röda kryssen sätts collissionboolean
		// till falsk för att stoppa loopen så inte tråden fortsätter att köras
		GameModel.MAZEMODEL.getMain().getStage().setOnCloseRequest(e -> {
			GameModel.MAZEMODEL.threadLoop = false;
		});

		root.setOnKeyPressed(keyEvent -> {
			if (GameModel.MAZEMODEL.keyEventHandling(keyEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.threadLoop = false;
				GameModel.MAZEMODEL.getMain().loadMaze("Maze2");

			}

		});

		// Metod för att köra med musen, tänkt att ta bort men det är lättare
		// att kolla igenom alla banor
		player.setOnMouseDragged(mouseEvent -> {
			if (GameModel.MAZEMODEL.mouseEventHandling(mouseEvent, lifeLeftLabel, gameOverLabel, player, map,
					finishLine) == true) {
				GameModel.MAZEMODEL.threadLoop = false;
				GameModel.MAZEMODEL.getMain().loadMaze("Maze2");
			}
		});

	}

	/**
	 * Overrides empty interface method, sets enemy translate transitions
	 * movements to enemies on the stage.
	 */

	// Skriver över den tomma metoden från controllerinterface för att få
	// funktion i metoden.
	@Override
	public void startEnemyMotions() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(5000), enemy1);
		tt.setByY(400);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(6000), enemy2);
		tt.setByX(390);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy3);
		tt.setByY(630);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
	}

	/**
	 * Overrides empty interface method, starts everything up on the maze
	 */
	@Override
	public void startUp() {

		GameModel.MAZEMODEL.threadLoop = true;
		GameModel.MAZEMODEL.setUpMenuItems(quitGame, restartMaze, startPage, rules, "Maze1");
		player.requestFocus();
		gameOverLabel.setVisible(false);
		lifeLeftLabel.setText("LIFE LEFT: " + Integer.toString((int) GameModel.MAZEMODEL.lives));
		startEnemyMotions();
		runCollision();

	}

	/**
	 * Overrides empty interface method, checks if player intersects with
	 * enemies.
	 */
	@Override
	public void enemyCollision() {
		if (GameModel.MAZEMODEL.collisionWithEnemy(player, enemy1, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy2, gameOverLabel, lifeLeftLabel) == true
				|| GameModel.MAZEMODEL.collisionWithEnemy(player, enemy3, gameOverLabel, lifeLeftLabel) == true) {
			GameModel.MAZEMODEL.threadLoop = false;
		} else
			GameModel.MAZEMODEL.threadLoop = true;

	}

	/**
	 * Overrrides empty interface method, runs enemycollision method on own
	 * thread.
	 */
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

	public Rectangle getEnemy1() {
		return enemy1;
	}

	public void setEnemy1(Rectangle enemy1) {
		this.enemy1 = enemy1;
	}

	public Rectangle getEnemy2() {
		return enemy2;
	}

	public void setEnemy2(Rectangle enemy2) {
		this.enemy2 = enemy2;
	}

	public Rectangle getEnemy3() {
		return enemy3;
	}

	public void setEnemy3(Rectangle enemy3) {
		this.enemy3 = enemy3;
	}

}
