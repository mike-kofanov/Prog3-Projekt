package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.gameIntro;

import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.quiz.QuizController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This Class is the Controller that represents the Game Intro Screen.
 * It is binded to the File "gameIntro.fxml"
 * @author Mike Kofanov
 */
public class GameIntroController {

    /** Instance of the GameController Class, that contains Data about the current quiz game session*/
    private GameController gameController = null;

    /**
     * Label in the UI in which the game instructions are displayed
     */
    @FXML
    private Label anleitungID;

    /**
     * Method that sets the instructions into the UI for game
     */
    public void setAnleitung(){
        anleitungID.setText("Hilf Fsociety dabei in das Mainframe von Evilcorp zu hacken!\n\n" +
                "Beantworte soviele Fragen wie möglich richtig, damit du das Spiel gewinnst.\n" +
                "Wenn die Antwort richtig ist wird sie Grün markiert.\n" +
                "Ist deine Antwort falsch so wird sie Rot markiert und\nzusätzlich wird die korrekte Antwort mit Grün markiert.\n\n" +
                "Aber vorsicht, die Zeit ist begrenzt!");
    }

    /**
     * This Method changes the Stage to Fsociety Menu Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Zurück' Button
     */
    @FXML
    void backToGameMenuButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToGameMenu();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * This Method changes the Stage to the Quiz Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Weiter' Button
     */
    @FXML
    void continueToGameButtonClicked(ActionEvent event) {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

        try {
            goToQuiz();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Getter for the instance of the GameController Object assigned to this Object.
     * @return GameController instance assigned for the current quiz game session
     */
    public GameController getGameController() {
        return gameController;
    }

    /**
     * Setter for the GameController attribute of the current instance of the GameIntroController Object
     * @param gameController GameController instance to be assigned to the current quiz game session
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /**
     * This Method proceeds to change the stage to the Quiz Screen
     * @throws IOException is thrown if a resource is not found.
     */
    private void goToQuiz() throws IOException {
        Stage stage = new Stage();
        stage.setTitle("Quiz");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/quiz/quiz.fxml").openStream());
        QuizController controller = fxmlLoader.getController();
        controller.setGameController(gameController);
        controller.setQuestions();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/quiz/quiz.css").toString());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * This Method resets the gameController and changes the Stage back to the Fsociety Menu Screen
     * @throws IOException is thrown if FXML resource is not found.
     */
    private void goToGameMenu() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/menu/fsocietyMenu.fxml").openStream());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Fsociety Menu");
        stage.setResizable(false);
        this.gameController = null;
        stage.show();
    }
}
