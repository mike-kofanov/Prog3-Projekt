package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.difficulty;

import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.gameIntro.GameIntroController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.GameController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.GameController.Difficulty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This class represents the Controller for Difficulty UI Screen.
 * It is connected to the File difficulty.fxml
 * @author Mike Kofanov
 */
public class DifficultyController {

    /**
     * This Method sets the 'Easy' Difficulty for the Quiz if the click event
     * is triggered by the user.
     * Proceeds to change stage to the Gameintro.
     * @param event click on the 'Easy' Button
     */
    @FXML
    void easyButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToGameIntro(Difficulty.EASY);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method sets the 'Hard' Difficulty for the Quiz if the click event
     * is triggered by the user.
     * Proceeds to change stage to the Gameintro.
     * @param event click on the 'Hard' Button
     */
    @FXML
    void hardButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToGameIntro(Difficulty.MEDIUM);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method sets the 'Medium' Difficulty for the Quiz if the click event
     * is triggered by the user.
     * Proceeds to change stage to the Gameintro.
     * @param event click on the 'Medium' Button
     */
    @FXML
    void mediumButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToGameIntro(Difficulty.HARD);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Helper Method to set Difficulty for the Quiz and proceed to change to Gameintro Stage.
     *
     * @param difficulty Win condition for current quiz game session (EASY, MEDIUM, HARD)
     * @throws IOException is thrown if resources could not be found
     */
    private void goToGameIntro(Difficulty difficulty) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/gameIntro/gameIntro.fxml").openStream());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Anleitung");
        stage.setResizable(false);
        GameIntroController controller = fxmlLoader.getController();
        controller.setGameController(new GameController(difficulty));
        controller.setAnleitung();
        stage.show();
    }
}
