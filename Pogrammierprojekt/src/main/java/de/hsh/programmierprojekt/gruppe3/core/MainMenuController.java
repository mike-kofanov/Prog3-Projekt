package de.hsh.programmierprojekt.gruppe3.core;

import de.hsh.programmierprojekt.gruppe3.products.snake.game.SnakeGame;
import de.hsh.programmierprojekt.gruppe3.products.worm.WormLauncher;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.Nonogramm;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.MainMenu;
/**
 * This Class is the Controller that represents the Spielesammlung. It is binded
 * to the File "gameEndScreen.fxml"
 * 
 * @author Mike Kofanov
 */
public class MainMenuController {

	@FXML
	private Button game1ID;

	@FXML
	private Button game2ID;

	@FXML
	private Button nonogrammID;

	@FXML
	private Button game4ID;

	/**
	 * This Object represents the Fsociety Button in the UI
	 * 
	 * @author Mike Kofanov
	 */
	@FXML
	private Button fsocietyButtonID;

	/**
	 * This Method exits the Spielesammlung and closes the program
	 * 
	 * @param event click on the 'Schließen' Button
	 * @author Mike Kofanov
	 */
	@FXML
	void exitButtonClicked(ActionEvent event) {
		Platform.exit();
	}

	/**
	 * This Method is the event handler for the 'FsocietyQuiz' Button It proceeds to
	 * change the stage to the fsociety menu screen.
	 * 
	 * @param event click on the 'Fsociety Button'
	 * @author Mike Kofanov
	 */
	@FXML
	void fsocietyGameButtonClicked(ActionEvent event) {
		Button button = (Button) event.getSource();
		Stage primaryStage = (Stage) button.getScene().getWindow();
		primaryStage.close();

		try {
			goToFsocietyQuizMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// TODO Stage wechseln zu eingem Spiel implementieren
	@FXML
	void game1ButtonClicked(ActionEvent event) {
		Button button = (Button) event.getSource();
		Stage primaryStage = (Stage) button.getScene().getWindow();
		primaryStage.close();
		Platform.setImplicitExit(false);
		new SnakeGame();
		
	}

	// TODO Stage wechseln zu eingem Spiel implementieren
	@FXML
	void game2ButtonClicked(ActionEvent event) {

		Button button = (Button) event.getSource();
		Stage primaryStage = (Stage) button.getScene().getWindow();
		primaryStage.close();
		MainMenu mainMenu = new MainMenu(primaryStage);
		mainMenu.getMainMenu();
	}

	// TODO Stage wechseln zu eingem Spiel implementieren
	@FXML
	void nonogrammButtonClicked(ActionEvent event) {
		Button button = (Button) event.getSource();
		Stage primaryStage = (Stage) button.getScene().getWindow();
		primaryStage.close();

		Platform.setImplicitExit(false);
		Nonogramm.launchNonogramm();
	}

	// TODO Stage wechseln zu eingem Spiel implementieren
	@FXML
	void game4ButtonClicked(ActionEvent event) {
		event.consume();
		try {
			Node source = (Node) event.getSource();
			var stage = WormLauncher.start((Stage) source.getScene().getWindow());
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Helper Method that changes the stage to the Fsociety Quiz Menu
	 * 
	 * @throws IOException is thrown if FXML resources are not found
	 * @author Mike Kofanov
	 */
	private void goToFsocietyQuizMenu() throws IOException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass()
				.getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/menu/fsocietyMenu.fxml")/// ..
				.openStream());
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Spielesammlung Dark IT");
		stage.setResizable(false);
		stage.show();
	}

	/**
	 * This Method sets all the backgroundImage for each game Button!
	 */
	public void setBackgroundImages() {
		// Fsociety
		String url = getClass().getResource("fsociety.jpg").toString();
		BackgroundImage myBI = new BackgroundImage(new Image(url, 100, 100, false, true), // 32 32
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		fsocietyButtonID.setBackground(new Background(myBI));

		// Nonogramm
		String nonoUrl = getClass().getResource("/nonogramm/image/title.png").toString();
		BackgroundImage nonoBI = new BackgroundImage(new Image(nonoUrl, 259, 98, false, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		nonogrammID.setBackground(new Background(nonoBI));

		//Virus Defender
		String vdUrl = getClass().getResource("/virus_defender_res/image/SPVDBBG.png").toString();
		BackgroundImage vdBI = new BackgroundImage(new Image(vdUrl, 270, 98, false, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		game2ID.setBackground(new Background(vdBI));
		// TODO Snake background Image implementieren sowie Methodenaufruf im eigenen
		String snakeUrl = getClass().getResource("/snake_res/image/snake.png").toString();
		BackgroundImage snakeBI = new BackgroundImage(new Image(snakeUrl, 259, 98, false, true), BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		game1ID.setBackground(new Background(snakeBI));
		// Spiel aufrufen!

		//Worm
		game4ID.setBackground(WormLauncher.getBackground());
		game4ID.setText("Worm");
		game4ID.setFont(Font.font(55));
		game4ID.setTextFill(Color.CORNFLOWERBLUE);
	}
}
