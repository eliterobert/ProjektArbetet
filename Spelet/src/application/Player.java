package application;

public class Player {

	private int points;
	private String name;

	/**
	 * Standard constructorn puts and default name on the player, and points to
	 * 300.
	 */
	public Player() {
		points = 300;
		name = "Default";
	}

	/**
	 * Parameterized constructor. Overloaded constructor for player, creates
	 * player with points and a name.
	 * 
	 * @param lives
	 *            Number of lives player has left
	 * @param name
	 *            name on player
	 */

	public Player(int lives, String name) {
		super();
		this.points = lives;
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int lives) {
		this.points = lives;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
