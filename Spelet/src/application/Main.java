package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class Main extends Application {

	private final int SCREEN_WIDTH = 1200;
	private final int SCREEN_HEIGHT = 830;
	private String mapPath;
	public static ArrayList<Scene> maps = new ArrayList<>();
	public static Stage stage;
	public static Scene startScene, scene1, scene2, scene3, scen4, scene4, scene5;
	public static Parent root;
	private URL location;
	private FXMLLoader loader;

	@Override
	public void start(Stage primaryStage) {
		try {

			GameModel model = new GameModel();
			model.startMediaPlayer();

			mapPath = "StartPage.fxml";
			location = this.getClass().getResource(mapPath);
			loader = new FXMLLoader(location);
			root = loader.load();
			Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
			scene1 = scene;
			scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage = primaryStage;
			stage.setScene(scene1);
			stage.setFullScreen(false);
			stage.show();
			root.requestFocus();
			loadMazes();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Exception Dialog");
			alert.setHeaderText("An exception occured!");
			alert.setContentText("Could not find file.");

			Exception ex = new FileNotFoundException("Could not find file");

			// Create expandable Exception.
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			ex.printStackTrace(pw);
			String exceptionText = sw.toString();

			Label label = new Label("The exception stacktrace was:");

			TextArea textArea = new TextArea(exceptionText);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);

			alert.showAndWait();

		}
	}

	private void loadMazes() {
		try {
			location = this.getClass().getResource("Maze1.fxml");
			loader = new FXMLLoader(location);
			root = loader.load();
			scene1 = new Scene(root, SCREEN_HEIGHT, SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_HEIGHT);
			stage.setMinWidth(SCREEN_WIDTH);
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

			maps.add(scene1);
			maps.add(scene2);
			maps.add(scene3);
			maps.add(scene4);
			maps.add(scene5);
		} catch (IOException e) {

			System.out.println("Could not load mazes");
		}

	}

	public ArrayList<Scene> getMaps() {
		return maps;
	}

	public void setMaps(ArrayList<Scene> maps) {
		this.maps = maps;
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
