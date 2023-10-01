package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.gameEnd;

import de.hsh.programmierprojekt.gruppe3.core.MainMenuController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.GameController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This Class is the Controller that represents the End Game Screen.
 * It is binded to the File "gameEndScreen.fxml"
 * @author Mike Kofanov
 */
public class GameEndController {

    /** Instance of the GameController Class, that contains Data about the current quiz game session*/
    private GameController gameController = null;

    /**
     * This Method sets the GameController to this current instance of the GameEndController
     * @param gameController Instance of the GameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /** Instance of an MediaPlayer Object, used to play sound */
    MediaPlayer mediaPlayer;

    /**
     * Label in the UI in which the endgame message is displayed
     */
    @FXML
    private Label successMessageId;

    /**
     * This Method changes the Stage to Spielesammlung Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Zurück zur Spielesammlung' Button
     * @throws IOException is thrown if FXML resource is not found.
     */
    @FXML
    void buttonBackToMainMenuClicked(ActionEvent event) throws IOException {
        Button button = (Button) event.getSource();
        Stage primaryStage = (Stage) button.getScene().getWindow();
        primaryStage.close();

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

    /**
     * This Method changes the Stage to Fsociety Menu Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Zurück zum Spielmenü' Button
     * @throws IOException is thrown if FXML resource is not found.
     */
    @FXML
    void buttonBackToMenuClicked(ActionEvent event) {
        mediaPlayer.stop();
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
     * This Method changes the current Stage to Fsociety Menu Screen
     * @throws IOException is thrown if an FXML Resource is not found
     */
    private void goToGameMenu() throws IOException {
        mediaPlayer.stop();
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

    /**
     * This Method sets the appropriate UI for gameEndScreen if the player won.
     * A positive Sound effect is played.
     */
    public void setGameIsWonUI(){
        successMessageId.setText("Herzlichen Glückwunsch!\nDank dir ist es Fsociety gelungen die Mainframe von EvilCorp zu hacken!\n" +
                "Du hast von " +  gameController.getQuestions().size() + " Fragen insgesamt "
                + gameController.getCorrectAnswersCounter() + " richtig beantwortet!");
        setGameStateSound();
    }

    /**
     * This Method sets the appropriate UI for gameEndScreen if the player won.
     * A negative Sound effect is played.
     */
    public void setGameIsLostUI(){
        successMessageId.setText("Leider ist es dir der Hack nicht gelungen und unser Netzwerk wurde nun entdeckt :(\n" +
                "Du hast von " +  gameController.getQuestions().size() + " Fragen insgesamt "
                + gameController.getCorrectAnswersCounter() + " richtig beantwortet!");
        setGameStateSound();
    }

    /**
     * This Method choses which Sound effect is played for the gameEnd Screen
     */
    private void setGameStateSound(){
        String soundfile;
        if(gameController.getIsGameWon()){
            soundfile = "gameWon.wav";
        } else {
            soundfile = "gameLost.wav";
        }
        Media sound = new Media(getClass().getResource(soundfile).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

}
