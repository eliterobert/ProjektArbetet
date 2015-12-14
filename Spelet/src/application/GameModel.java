package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

public class GameModel {

	Media anventureSong;
	MediaPlayer mediaPlayer;
	Path p1, p2;
	double lives = 200;
	PerspectiveCamera camera = new PerspectiveCamera(false);

	public PerspectiveCamera getCamera() {
		return camera;
	}

	public void setCamera(PerspectiveCamera camera) {
		this.camera = camera;
	}

	boolean checkCollisionWithArrow(KeyEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle rectangle,
			SVGPath map, Rectangle finishLine) {

		KeyCode key = event.getCode();
		setPathes(map, rectangle, finishLine);
		if (!p2.getElements().isEmpty()) {
			mazeWon(gameOverLabel, rectangle);
			return true;
		}

		if (!p1.getElements().isEmpty() && lives != 0) {
			if (key == KeyCode.W) {
				rectangle.setY(rectangle.getY() + 12);
			} else if (key == KeyCode.A) {
				rectangle.setX(rectangle.getX() + 12);
			} else if (key == KeyCode.S) {
				rectangle.setY(rectangle.getY() - 12);
			} else if (key == KeyCode.D) {
				rectangle.setX(rectangle.getX() - 12);
			}
			lives -= 0.6;
			lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
			if (lives <= 0) {
				gameOverLabel.setVisible(true);
				rectangle.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		}
		if (key == KeyCode.W) {
			rectangle.setY(rectangle.getY() - 4);
		} else if (key == KeyCode.A) {
			rectangle.setX(rectangle.getX() - 4);
		} else if (key == KeyCode.S) {
			rectangle.setY(rectangle.getY() + 4);
		} else if (key == KeyCode.D) {
			rectangle.setX(rectangle.getX() + 4);
		}

		return false;
	}

	void startMediaPlayer() {

		anventureSong = new Media(new File("src/Music/Adventure.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(anventureSong);
		mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		mediaPlayer.play();

	}

	private void setDisableKeys(KeyCode notDisabled, KeyCode dis1, KeyCode dis2, KeyCode dis3) {

	}

	void camera(Scene scene) {

		scene.setCamera(camera);

	}

	boolean checkCollisionWithMouse(MouseEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle rectangle,
			SVGPath map, Rectangle finishLine) {

		setPathes(map, rectangle, finishLine);
		lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
		lifeLeftLabel.requestFocus();
		if (!p2.getElements().isEmpty()) {
			mazeWon(gameOverLabel, rectangle);
			return true;
		}
		if (!p1.getElements().isEmpty() && lives != 0) {
			lives -= 1;
			lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
			rectangle.setX(event.getX());
			rectangle.setY(event.getY());
			if (lives < 0) {
				gameOverLabel.setVisible(true);
				rectangle.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		} else {
			rectangle.setX(event.getX());
			rectangle.setY(event.getY());
			camera.setTranslateX(rectangle.getX() / 2);
			camera.setTranslateY(rectangle.getY() / 2);
		}
		return false;

	}

	private boolean mazeWon(Label gameOverLabel, Rectangle rectangle) {

		gameOverLabel.setVisible(true);
		rectangle.setVisible(false);
		return true;
	}

	private void setPathes(SVGPath map, Rectangle rectangle, Rectangle finishLine) {
		p1 = (Path) Shape.intersect(map, rectangle);
		p2 = (Path) Shape.intersect(rectangle, finishLine);
	}
}
