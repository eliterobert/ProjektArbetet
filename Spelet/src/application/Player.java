package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player {

	private ImageView graphics = new ImageView();
	private Image frames[];
	private int frameCounter = 0;
	public boolean moving = false;

	public Player(Image[] frames, Rectangle rectangle) {
		this.frames = frames;
		graphics.setImage(frames[0]);
		rectangle.setFill(Color.TRANSPARENT);
		rectangle.setStroke(Color.BLACK);
		rectangle.translateXProperty().bind(graphics.translateXProperty().add(frames[0].getWidth() / 2.0));
		rectangle.translateYProperty().bind(graphics.translateYProperty().add(12.0));
		rectangle.rotateProperty().bind(graphics.rotateProperty());
	}

	public ImageView getGraphics() {
		return graphics;
	}

	public void setGraphics(ImageView graphics) {
		this.graphics = graphics;
	}

	public Image[] getFrames() {
		return frames;
	}

	public void setFrames(Image[] frames) {
		this.frames = frames;
	}

	public int getFrameCounter() {
		return frameCounter;
	}

	public void setFrameCounter(int frameCounter) {
		this.frameCounter = frameCounter;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void refreshPlayer() {
		graphics.setImage(frames[frameCounter++]);
		if (frameCounter == 3) {
			frameCounter = 0;
		}

	}

}
