package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GameModel;
import application.Main;
import application.StartPageModel;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Controller implements Initializable, ControllerInterface {

	@FXML
	SVGPath map;
	@FXML
	Rectangle rectangle, finishLine, enemy1, enemy2, enemy3;
	@FXML
	AnchorPane root;
	@FXML
	Label gameOverLabel, lifeLeftLabel;
	GameModel model;
	volatile Thread thread;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		model = new GameModel();
		rectangle.requestFocus();
		gameOverLabel.setVisible(false);

		startEnemyMotions();
		root.setOnKeyPressed(keyEvent -> {
			enemyCollision();
			if (model.checkCollisionWithArrow(keyEvent, lifeLeftLabel, gameOverLabel, rectangle, map,
					finishLine) == true) {
				Main.stage.setScene(Main.maps.get(1));
				Main.root.requestFocus();
				Main.stage.setFullScreen(true);

			}

		});
		rectangle.setOnMouseDragged(mouseEvent -> {
			model.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, rectangle, map, finishLine);
		});

	}

//	void runCollision(boolean test) {
//		thread = new Thread(() -> {
//			while (test) {
//				enemyCollision();
//			}
//		});
//		if (test == true)
//			thread.start();
//		else if (test == false) {
//
//		}
//
//	}

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

	@Override
	public void enemyCollision() {
		model.collisionWithEnemy(rectangle, enemy1, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy2, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy3, gameOverLabel, lifeLeftLabel);
	}

}
