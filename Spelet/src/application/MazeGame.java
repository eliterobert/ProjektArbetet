package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Starts application, sets start stage
 * 
 * @author Robert E
 * @version 1.0 @
 *
 */
public class MazeGame extends Application {

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 830;
	/**
	 * Static stages needs to be use the same object from time to time.
	 */
	public static Stage stage, rulesStage;
	private Scene startScene, gameScene;
	private Parent root;
	private URL location;
	private FXMLLoader loader;
	private StartPageModel spm;

	/**
	 * Loads scene to fxmlloader. sets scene and starts application.
	 * 
	 * @param primaryStage
	 *            the primary stage for application
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			spm = new StartPageModel();
			location = this.getClass().getResource("/mazes/Startpage.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			startScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			startScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(startScene);
			stage.setFullScreen(false);
			stage.show();
			root.requestFocus();
		} catch (Exception e) {
			spm.loadErrorWindow(e);
		}
	}

	/**
	 * uses static launch
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Loads stage that shows the game rules
	 */
	public void loadRules() {
		rulesStage = new Stage();
		location = this.getClass().getResource("/mazes/GameRules.fxml");
		loader = new FXMLLoader(location);
		try {
			root = loader.load();
		} catch (IOException e) {
			spm.loadErrorWindow(e);
		}
		Scene rulesScene = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
		rulesStage.setMinWidth(260);
		rulesStage.setMinHeight(550);
		rulesStage.setMaxHeight(550);
		rulesStage.setMaxWidth(260);
		rulesScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		rulesStage.setScene(rulesScene);
		rulesStage.show();

	}

	/**
	 * Load stage that is the maze
	 * 
	 * @param mazeName
	 *            name of the maze you want to show
	 */
	public void loadMaze(String mazeName) {
		try {
			location = this.getClass().getResource("/mazes/" + mazeName + ".fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			gameScene = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
			stage.setScene(gameScene);
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setFullScreen(true);
			root.requestFocus();
		} catch (IOException e) {
			spm.loadErrorWindow(e);
		}
	}

	/**
	 * Load the startpage
	 */
	public void loadStartPage() {
		location = this.getClass().getResource("/mazes/StartPage.fxml");
		loader = new FXMLLoader(location);
		try {
			root = loader.load();
		} catch (IOException e) {
			spm.loadErrorWindow(e);
		}
		startScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		startScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(startScene);
		stage.setFullScreen(false);
		stage.show();
		root.requestFocus();
	}

	public Stage getStage() {
		return stage;
	}
}
