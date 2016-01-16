package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.LinkedList;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Model for the start page
 * 
 * @author Robert E
 * @version 1.0
 *
 */
public class StartPageModel {

	/**
	 * Loads highscore to textarea in start page
	 * 
	 * @param scores
	 *            Linked list with info about player name, points and time.
	 * @param highScoreArea
	 *            Textarea on start page that shows highscore
	 */
	public void loadHighScore(LinkedList<String> scores, TextArea highScoreArea) {
		try (BufferedReader buff = new BufferedReader(new FileReader("src/Textfiles/WallOfFame.txt"))) {
			String tempText = "";
			while ((tempText = buff.readLine()) != null) {
				scores.add(tempText);
				Collections.sort(scores);
				Collections.reverse(scores);
			}
		} catch (FileNotFoundException ex) {
			loadErrorWindow(ex);
		} catch (IOException ex) {
			loadErrorWindow(ex);
		}
		while (!scores.isEmpty()) {
			highScoreArea.appendText(scores.poll() + "\n");
		}

	}

	/**
	 * Reads recent players to combobox on start page
	 * 
	 * @param recentPlayerBox
	 *            the combobox on start page
	 */
	public void readRecentPlayersToComboBox(ComboBox<String> recentPlayerBox) {
		try (BufferedReader buff = new BufferedReader(new FileReader("src/Textfiles/RecentPlayers.txt"))) {
			String tempText = "";
			while ((tempText = buff.readLine()) != null) {
				recentPlayerBox.getItems().add(tempText);
			}
		} catch (FileNotFoundException ex) {
			loadErrorWindow(ex);
		} catch (IOException ex) {
			loadErrorWindow(ex);
		}

	}

	/**
	 * Writes player name to recentplayers.txt
	 * 
	 * @param currentPlayer
	 *            the player that is currently playing
	 */
	public void playerToRecentPlayers(Player currentPlayer) {
		try (BufferedWriter bw = new BufferedWriter(
				new FileWriter(new File("src/Textfiles/RecentPlayers.txt"), true))) {
			bw.append(currentPlayer.getName() + "\n");
		} catch (IOException e1) {
			loadErrorWindow(e1);
		}
	}

	private void loadErrorWindow(Exception e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("An exception occured!");
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
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
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();

	}

}
