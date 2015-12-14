package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.TilePane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {

	static Stage _primaryStage;

	@Override
	public void start(Stage primaryStage) {
		try {

			URL location = this.getClass().getResource("EmmasBana2.fxml");
			FXMLLoader loader = new FXMLLoader(location);
			Parent root = loader.load();
			Scene scene = new Scene(root, 800, 600);
			GameModel c = new GameModel();
			c.camera(scene);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
