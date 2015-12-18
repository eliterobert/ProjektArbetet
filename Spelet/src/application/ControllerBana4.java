package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;

public class ControllerBana4 implements Initializable {

	@FXML
	Slider speedSlider;
	@FXML
	SVGPath map;
	@FXML
	Rectangle rectangle, finishLine;
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

		root.setOnKeyPressed(keyEvent -> {
			if (model.checkCollisionWithArrow(keyEvent, lifeLeftLabel, gameOverLabel, rectangle, map,
					finishLine) == true) {
				Main.stage.setScene(Main.scene5);
				Main.root.requestFocus();
				Main.stage.setFullScreen(true);
				model.mediaPlayer.stop();

			}

		});
		rectangle.setOnMouseDragged(mouseEvent -> {

			model.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, rectangle, map, finishLine);

		});
	}

}
