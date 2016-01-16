package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import application.MazeGame;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * Controller for rules page, shows rules and sets action on closebutton.
 * 
 * @author Robert E
 * @version 1.0
 */
public class GameRulesController implements Initializable {

	@FXML
	Button closeButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		closeButton.setOnAction(e -> {

			MazeGame.rulesStage.close();

		});

	}

}
