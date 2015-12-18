package application;

import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 830;
	private String mapPath;
	ArrayList<String> maps = new ArrayList<>();
	static Stage stage;
	static Scene theScene, scene2, scene3, scen4, scene4,scene5;
	static Parent root;

	@Override
	public void start(Stage primaryStage) {
		try {
			
			GameModel model = new GameModel();
			
			Thread mediaThread = new Thread(()-> {
				model.startMediaPlayer();
			});
			mediaThread.start();

			mapPath = "Maze1.fxml";
			URL location = this.getClass().getResource(mapPath);
			FXMLLoader loader = new FXMLLoader(location);
			root = loader.load();
			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			theScene = scene;
			theScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(theScene);
			stage.setFullScreen(true);
			stage.show();
			root.requestFocus();

			location = this.getClass().getResource("Maze2.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			scene2 = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
			root.requestFocus();

			location = this.getClass().getResource("Maze3.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			scene3 = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
			root.requestFocus();
			
			location = this.getClass().getResource("Maze4.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			scene4 = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
			root.requestFocus();
			
			location = this.getClass().getResource("Maze5.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			scene5 = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
			root.requestFocus();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Parent getRoot() {
		return root;
	}

	public static void main(String[] args) {
		launch(args);
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
