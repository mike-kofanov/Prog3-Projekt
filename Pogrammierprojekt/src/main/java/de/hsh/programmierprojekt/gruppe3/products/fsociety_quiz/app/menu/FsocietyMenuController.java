package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.menu;

import de.hsh.programmierprojekt.gruppe3.core.MainMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
* This Class is the Controller that represents the Fsociety Menu Screen.
* It is binded to the File "fsocietyMenu.fxml"
* @author Mike Kofanov
*/
public class FsocietyMenuController {

    /**
     * This Method changes the Stage to Spielesammlung Menu Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Zurück' Button
     */
    @FXML
    void backButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToSpieleSammlungMenu();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method changes the Stage to Game Intro Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Starte Quiz' Button
     */
    @FXML
    void startQuizButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToDifficultyScreen();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method proceeds to change the stage to the Difficulty Screen
     * @throws IOException is thrown if a FXML resource is not found.
     */
    private void goToDifficultyScreen() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/difficulty/difficulty.fxml").openStream());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Wähle deine Schwierigkeitsstufe");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This Method proceeds to change the stage to the Spielesammlung Screen
     * @throws IOException is thrown if a FXML resource is not found.
     */
    private void goToSpieleSammlungMenu() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/core/mainMenu.fxml").openStream());
        MainMenuController controller = fxmlLoader.getController();
        controller.setBackgroundImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Spielesammlung Dark IT");
        stage.setResizable(false);
        stage.show();
    }

}
