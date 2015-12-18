package application;

import javafx.scene.image.Image;

public class Resourses {

	public Image playerImg[] = new Image[3];

	public Resourses() {
		try {
			for (int i = 0; i < playerImg.length; i++) {
				playerImg[i] = new Image("PlayerFrames/playerFrame" + i + ".png/");
			}

		} catch (Exception e) {
			System.out.println("Problem in loading resourses");
		}
	}

}
