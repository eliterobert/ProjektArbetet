package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	private final int SCREEN_WIDTH = 1024;
	private final int SCREEN_HEIGHT = 768;
	private String mapPath;
	static Stage stage;
	static Scene theScene;
	static Parent root;

	@Override
	public void start(Stage primaryStage) {
		try {

			mapPath = "RobertsBana.fxml";
			URL location = this.getClass().getResource(mapPath);
			FXMLLoader loader = new FXMLLoader(location);
			root = loader.load();

			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

			theScene = scene;
			theScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(theScene);
			stage.show();
			root.requestFocus();

			location = this.getClass().getResource("EmmasBana.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			theScene = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Parent getRoot() {
		return root;
	}

	public void setRoot(Parent root) {
		this.root = root;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Scene getScene() {
		return theScene;
	}

	public void setScene(Scene scene) {
		this.theScene = scene;
	}

	public String getMapPath() {
		return mapPath;
	}

	public void setMapPath(String mapPath) {
		this.mapPath = mapPath;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
