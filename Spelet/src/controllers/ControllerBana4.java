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

public class ControllerBana4 implements Initializable, ControllerInterface {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle rectangle, finishLine, enemy1, enemy2, enemy3, enemy4, enemy5;
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
				Main.stage.setScene(Main.scene5);
				Main.root.requestFocus();
				Main.stage.setFullScreen(true);
			}

		});
		rectangle.setOnMouseDragged(mouseEvent -> {

			model.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, rectangle, map, finishLine);

		});
	}

	@Override
	public void startEnemyMotions() {
		TranslateTransition tt = new TranslateTransition(Duration.millis(4896), enemy1);
//		tt.setDelay(Duration.millis(3500));
//		tt.setByY(605);
//		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);

		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3000), enemy2);
		tt.setByX(-235);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(3500), enemy3);
		tt.setByX(-150);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(495), enemy4);
		tt.setByX(40);
		tt.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		tt.setAutoReverse(true);
		tt.play();
		tt = new TranslateTransition(Duration.millis(2000), enemy5);
		tt.setByY(-40);
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
	}

}
