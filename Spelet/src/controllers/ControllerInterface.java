package controllers;

/**
 * Interface to the mazes with methods that is needed to play
 * 
 * @author Robert E
 * @version 1.0
 *
 */
public interface ControllerInterface {

	/**
	 * Method use to start enemy movement
	 */
	void startEnemyMotions();

	/**
	 * Method use to check if player intersect with enemy
	 */
	void enemyCollision();

	/**
	 * Method use to start collision
	 */
	void runCollision();

	/**
	 * Method add everything needed to start the maze
	 */
	void startUp();

}
