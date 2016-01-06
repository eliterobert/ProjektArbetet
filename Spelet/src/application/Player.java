package application;

public class Player {

	private int lives;
	private String name;

	public Player(int lives, String name) {
		super();
		this.lives = lives;
		this.name = name;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
