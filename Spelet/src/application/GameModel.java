package application;

import java.io.File;

import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
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

	void checkCollisionWithArrow(KeyEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle rectangle,
			SVGPath map, Rectangle finishLine) {

		setPathes(map, rectangle, finishLine);
		if (!p2.getElements().isEmpty()) {
			mazeWon(gameOverLabel, rectangle);
		}
		if (!p1.getElements().isEmpty() && lives != 0) {
			lives -= 1;
			lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
			if (lives < 0) {
				gameOverLabel.setVisible(true);
				rectangle.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		}
		KeyCode key = event.getCode();
		if (key == KeyCode.W) {
			rectangle.setY(rectangle.getY() - 4);
		} else if (key == KeyCode.A) {
			rectangle.setX(rectangle.getX() - 4);
		} else if (key == KeyCode.S) {
			rectangle.setY(rectangle.getY() + 4);
		} else if (key == KeyCode.D) {
			rectangle.setX(rectangle.getX() + 4);
		}
	}

	void startMediaPlayer() {

		anventureSong = new Media(new File("src/Music/Adventure.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(anventureSong);
		mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		mediaPlayer.play();

	}

	void camera(Scene scene) {

		scene.setCamera(camera);

	}

	void checkCollisionWithMouse(MouseEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle rectangle,
			SVGPath map, Rectangle finishLine) {

		setPathes(map, rectangle, finishLine);

		lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
		lifeLeftLabel.requestFocus();
		if (!p2.getElements().isEmpty()) {
			mazeWon(gameOverLabel, rectangle);
		}
		if (!p1.getElements().isEmpty() && lives != 0) {
			lives -= 0.4;
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
	}

	private void mazeWon(Label gameOverLabel, Rectangle rectangle) {
		gameOverLabel.setText("Maze Done!");
		gameOverLabel.setVisible(true);
		rectangle.setVisible(false);
	}

	private void setPathes(SVGPath map, Rectangle rectangle, Rectangle finishLine) {
		p1 = (Path) Shape.intersect(map, rectangle);
		p2 = (Path) Shape.intersect(rectangle, finishLine);
	}
}
