package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

public class Controller implements Initializable {

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
		model.startMediaPlayer();
		rectangle.requestFocus();
		gameOverLabel.setVisible(false);

		root.setOnKeyPressed(keyEvent -> {
			if (model.checkCollisionWithArrow(keyEvent, lifeLeftLabel, gameOverLabel, rectangle, map,
					finishLine) == true) {
				Main.stage.setScene(Main.theScene);
				Main.root.requestFocus();
			}

		});
		rectangle.setOnMouseDragged(mouseEvent -> {
			model.checkCollisionWithMouse(mouseEvent, lifeLeftLabel, gameOverLabel, rectangle, map, finishLine);

		});
	}

}
