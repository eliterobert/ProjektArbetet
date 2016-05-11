package application;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.scene.shape.Shape;

/**
 * Singelton that is used by mazes to check collissions, set lives to player.
 * 
 * @author Robert E
 * @version 1.0
 *
 */
public enum GameModel {

	MAZEMODEL;

	// Aggregation with main so i can use methods from main class, because it is
	// in a singleton i don't need or can create multiple main objects.
	private MazeGame main = new MazeGame();

	private int currentX, currentY;
	private Path p1, p2, p3;

	/**
	 * Number of lives player have each game
	 */
	public double lives = 300;

	/**
	 * Boolean that sets while loop to true while player isn't die or reach
	 * finish line. Sets to false if player dies/quits game.
	 */
	public boolean threadLoop;

	/**
	 * Checks if player hits the wall or not, and if so moves the player back
	 * 
	 * @param event
	 *            Keyevent from user
	 * @param lifeLeftLabel
	 *            Label on each scene showing Life left
	 * @param gameOverLabel
	 *            Label that shows game over over the screen if dead
	 * @param player
	 *            Rectangle that is the player
	 * @param maze
	 *            The SVGpath that is the maze
	 * @param finishLine
	 *            Rectangle that is the finish line in each maze
	 * @return returns true if player hits the finish line, returns false if
	 *         not.
	 */

	public boolean keyEventHandling(KeyEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle player,
			SVGPath maze, Rectangle finishLine) {

		KeyCode key = event.getCode();

		setPathes(maze, player, finishLine);

		if (!p2.getElements().isEmpty()) {
			return true;
		}

		if (!p1.getElements().isEmpty() && lives != 99) {
			if (player.getY() < currentY) {
				rectangleBounceOnWall(player);
			} else if (player.getY() > currentY) {
				rectangleBounceOnWall(player);
			} else if (player.getX() < currentX) {
				rectangleBounceOnWall(player);
			} else if (player.getX() > currentX) {
				rectangleBounceOnWall(player);
			}

			lives -= 1;
			lifeLeftLabel.setText("LIFE LEFT: " + Math.round(lives) / 1);
			if (lives <= 100) {
				gameOverLabel.setVisible(true);
				player.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		}

		if (key == KeyCode.W || key == KeyCode.UP) {
			player.setY(player.getY() - 5);
			currentY = (int) (player.getY() + 6);
		} else if (key == KeyCode.A || key == KeyCode.LEFT) {
			player.setX(player.getX() - 5);
			currentX = (int) (player.getX() + 6);
		} else if (key == KeyCode.S || key == KeyCode.DOWN) {
			player.setY(player.getY() + 5);
			currentY = (int) (player.getY() - 6);
		} else if (key == KeyCode.D || key == KeyCode.RIGHT) {
			player.setX(player.getX() + 5);
			currentX = (int) (player.getX() - 6);
		}

		return false;
	}

	/**
	 * 
	 * @param player
	 *            Rectangle that is the player
	 * @param enemy
	 *            Rectangle that is the enemy
	 * @param gameOverLabel
	 *            Label that shows game over over the screen if dead
	 * @param lifeLeftLabel
	 *            Label on each scene showing Life left
	 * @return Returns true if player intersect with an enemy, returns false if
	 *         not.
	 */
	public boolean collisionWithEnemy(Rectangle player, Rectangle enemy, Label gameOverLabel, Label lifeLeftLabel) {
		p3 = (Path) Shape.intersect(enemy, player);
		if (!p3.getElements().isEmpty()) {
			lifeLeftLabel.setVisible(false);
			player.setVisible(false);
			gameOverLabel.setVisible(true);
			return true;
		} else
			return false;
	}

	/**
	 * Player can be moved by mouse
	 * 
	 * @param event
	 *            Keyevent from user
	 * @param lifeLeftLabel
	 *            Label on each scene showing Life left
	 * @param gameOverLabel
	 *            Label that shows game over over the screen if dead
	 * @param player
	 *            Rectangle that is the player
	 * @param maze
	 *            The SVGpath that is the maze
	 * @param finishLine
	 *            Rectangle that is the finish line in each maze
	 * @return returns true if player hits the finish line, returns false if
	 *         not.
	 */

	public boolean mouseEventHandling(MouseEvent event, Label lifeLeftLabel, Label gameOverLabel, Rectangle player,
			SVGPath maze, Rectangle finishLine) {

		// Denna metod är mer för att kunna kolla igenom banorna mer än att
		// kunna spela med musen.

		setPathes(maze, player, finishLine);
		lifeLeftLabel.setText("LIFE LEFT: " + Math.round(lives) / 1);
		lifeLeftLabel.requestFocus();
		if (!p2.getElements().isEmpty()) {
			return true;
		}
		if (!p1.getElements().isEmpty() && lives != 0) {
			lifeLeftLabel.setText("LIFE LEFT: " + Math.round(lives) / 1);
			player.setX(event.getX() + 5);
			player.setY(event.getY() + 5);
			if (lives < 0) {
				gameOverLabel.setVisible(true);
				player.setVisible(false);
				lifeLeftLabel.setVisible(false);
			}
		} else {
			player.setX(event.getX());
			player.setY(event.getY());
		}
		return false;

	}

	/**
	 * Sets action events on menu items for menus
	 * 
	 * @param quitGame
	 *            Menu item for quitting game
	 * @param restartMaze
	 *            Menu item for restart current maze
	 * @param startPage
	 *            Menu item to send player to start page
	 * @param rules
	 *            Menu item that shows the rules of the game
	 * @param mazeName
	 *            Witch maze you should be sent to
	 */
	public void setUpMenuItems(MenuItem quitGame, MenuItem restartMaze, MenuItem startPage, MenuItem rules,
			String mazeName) {

		quitGame.setOnAction(e -> {
			threadLoop = false;
			Platform.exit();
		});
		restartMaze.setOnAction(e -> {
			lives = 150;
			main.loadMaze(mazeName);
		});
		startPage.setOnAction(e -> {
			threadLoop = false;
			main.loadStartPage();
		});
		rules.setOnAction(e -> {
			main.loadRules();
		});

	}

	private void rectangleBounceOnWall(Rectangle rectangle) {
		rectangle.setY(currentY);
		rectangle.setX(currentX);
	}

	private void setPathes(SVGPath map, Rectangle player, Rectangle finishLine) {
		p1 = (Path) Shape.intersect(map, player);
		p2 = (Path) Shape.intersect(player, finishLine);
	}

	public MazeGame getMain() {
		return main;
	}

	public void setMain(MazeGame main) {
		this.main = main;
	}

	public double getLives() {
		return lives;
	}

	public void setLives(double lives) {
		this.lives = lives;
	}
}
