package application;

import java.io.File;

import controllers.Controller;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
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
	Path p1, p2, p3;
	double lives = 200;

	public double getLives() {
		return lives;
	}

	public void setLives(double lives) {
		this.lives = lives;
	}

	int currentX, currentY;
	Controller cont = new Controller();

	public boolean checkCollisionWithArrow(KeyEvent event, Label lifeLeftLabel, Label gameOverLabel,
			Rectangle rectangle, SVGPath map, Rectangle finishLine) {
		KeyCode key = event.getCode();

		setPathes(map, rectangle, finishLine);

		if (!p2.getElements().isEmpty()) {
			mazeWon(gameOverLabel, rectangle);
			return true;
		}

		if (!p1.getElements().isEmpty() && lives != 0) {
			if (rectangle.getY() < currentY) {
				setRectangleToCurrentToCurrentPosition(rectangle);
			} else if (rectangle.getY() > currentY) {
				setRectangleToCurrentToCurrentPosition(rectangle);
			} else if (rectangle.getX() < currentX) {
				setRectangleToCurrentToCurrentPosition(rectangle);
			} else if (rectangle.getX() > currentX) {
				setRectangleToCurrentToCurrentPosition(rectangle);
			}

			lives -= 1;
			lifeLeftLabel.setText("LIV KVAR: " + Math.round(lives) / 1);
			if (lives <= 0) {
				gameOverLabel.setVisible(true);
				rectangle.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		}

		if (key == KeyCode.W || key == KeyCode.UP) {
			rectangle.setY(rectangle.getY() - 5);
			currentY = (int) (rectangle.getY() + 6);
		} else if (key == KeyCode.A || key == KeyCode.LEFT) {
			rectangle.setX(rectangle.getX() - 5);
			currentX = (int) (rectangle.getX() + 6);
		} else if (key == KeyCode.S || key == KeyCode.DOWN) {
			rectangle.setY(rectangle.getY() + 5);
			currentY = (int) (rectangle.getY() - 6);
		} else if (key == KeyCode.D || key == KeyCode.RIGHT) {
			rectangle.setX(rectangle.getX() + 5);
			currentX = (int) (rectangle.getX() - 6);
		}

		return false;
	}

	void startMediaPlayer() {

		anventureSong = new Media(new File("src/Music/Adventure.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(anventureSong);
		mediaPlayer.setCycleCount(javafx.scene.media.MediaPlayer.INDEFINITE);
		mediaPlayer.play();

	}

	public void collisionWithEnemy(Rectangle player, Rectangle enemy, Label gameOverLabel, Label lifeLeftLabel) {
		p3 = (Path) Shape.intersect(enemy, player);
		if (!p3.getElements().isEmpty()) {
			lives = 0;
			player.setVisible(false);
			gameOverLabel.setVisible(true);
			lifeLeftLabel.setText("Dead");
		}

	}

	public boolean checkCollisionWithMouse(MouseEvent event, Label lifeLeftLabel, Label gameOverLabel,
			Rectangle rectangle, SVGPath map, Rectangle finishLine) {

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
			rectangle.setX(event.getX() + 5);
			rectangle.setY(event.getY() + 5);
			if (lives < 0) {
				gameOverLabel.setVisible(true);
				rectangle.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		} else {
			rectangle.setX(event.getX());
			rectangle.setY(event.getY());
		}
		return false;

	}

	private void mazeWon(Label gameOverLabel, Rectangle rectangle) {
		gameOverLabel.setText("Maze Done");
		gameOverLabel.setVisible(true);
		rectangle.setVisible(false);
	}

	private void setRectangleToCurrentToCurrentPosition(Rectangle rectangle) {
		rectangle.setY(currentY);
		rectangle.setX(currentX);
	}

	private void setPathes(SVGPath map, Rectangle rectangle, Rectangle finishLine) {
		p1 = (Path) Shape.intersect(map, rectangle);
		p2 = (Path) Shape.intersect(rectangle, finishLine);
	}
}
