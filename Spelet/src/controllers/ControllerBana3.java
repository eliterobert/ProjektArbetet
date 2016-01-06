package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.GameModel;
import application.Main;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class ControllerBana3 implements Initializable, ControllerInterface {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle rectangle, finishLine, enemy1, enemy2, enemy3, enemy4, enemy5, enemy6;
	@FXML
	AnchorPane root;
	@FXML
	Label gameOverLabel, lifeLeftLabel;
	GameModel model;

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
				Main.stage.setScene(Main.scene4);
				Main.root.requestFocus();
				Main.stage.setFullScreen(true);

			}

		});
		rectangle.setOnMouseDragged(mouseEvent -> {
			model.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, rectangle, map, finishLine);
		});
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
		model.collisionWithEnemy(rectangle, enemy1, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy2, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy3, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy4, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy5, gameOverLabel, lifeLeftLabel);
		model.collisionWithEnemy(rectangle, enemy6, gameOverLabel, lifeLeftLabel);
	}
}
