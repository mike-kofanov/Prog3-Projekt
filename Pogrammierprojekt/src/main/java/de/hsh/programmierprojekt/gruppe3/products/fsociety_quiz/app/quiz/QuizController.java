package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.quiz;

import de.hsh.programmierprojekt.gruppe3.core.MainMenuController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.app.gameEnd.GameEndController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.Answer;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.GameController;
import de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model.Question;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class is the Controller that represents the Quiz Screen.
 * It is binded to the File "quiz.fxml"
 * @author Mike Kofanov
 */
public class QuizController {

    /** Instance of the GameController Class, that contains Data about the current quiz game session*/
    private GameController gameController = null;

    /**
     * This Method sets the GameController to this current instance of the QuizController
     * @param gameController Instance of the GameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    /** Timer in ms, for delay before transition to next question */
    private static int TIMER = 500;

    /** Contains an ArrayList with all questions and their respective answers */
    private ArrayList<Question> questionsList = new ArrayList();

    /** Controller var if user picked an answer */
    private boolean pickedAnswer = false;
    /**Stores index of current question*/
    private int currentQuestion = 0;
    /** Stores amount of total questions present in questionList */
    private int maxQuestions = 0;

    /** Boolean value of 4 Answers to each question */
    private boolean[] answersBoolAray = new boolean[4];
    /** Boolean value of answer1 Button*/
    private boolean answer1;
    /** Boolean value of answer2 Button*/
    private boolean answer2;
    /** Boolean value of answer3 Button*/
    private boolean answer3;
    /** Boolean value of answer4 Button*/
    private boolean answer4;

    /** Logic var to deactivate handler during runtime */
    private boolean isHandlerActive = true;

    /** Instance of an MediaPlayer Object, used to play sound */
    private MediaPlayer mediaPlayer = null;

    /** Internal Logic Controller in this class for the Timeoutbar */
    private boolean isAnyAnswerButtonClicked = false;
    /** Internal Logic Controller in this class for the Timeoutbar */
    private boolean isTimeOutBarZero = false;

    /** Instance of the Timeline Class used in the Timeoutbar animation */
    Timeline timeline = new Timeline();

    /** Represent the timeoutbar */
    @FXML
    private ProgressBar timeOutBar;

    /** Represent answer1Button*/
    @FXML
    private Button answer1Button;

    /** Represent answer1Button*/
    @FXML
    private Button answer2Button;

    /** Represent answer1Button*/
    @FXML
    private Button answer3Button;

    /** Represent answer1Button*/
    @FXML
    private Button answer4Button;

    /** Represent answer1Button*/
    @FXML
    private Label questionLabel;

    /** Represent Label where current Question is displayed in the UI*/
    @FXML
    private Label questionIndex;

    /** Saves mouse click event during runtime for later UI handling */
    private ActionEvent closeStageEvent;

    /************************* Event handlers **********************************/


    /**
     * This Method changes the Stage to Spielesammlung Screen if the click event
     * is triggered by the user.
     * @param event click on the 'Spiel verlassen' Button
     * @throws IOException is thrown if FXML resource is not found.
     */
    @FXML
    public void leaveGameButtonClicked(ActionEvent event) throws IOException {
        timeline.stop();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
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
     * Catches click event from the User on the answer Buttons and handles it.
     *
     * Correct answer is displayed by the Button turning green with a positive sound effect
     * and wrong answer is displayed by the Button turning red with a negative sound effect.
     *
     * If user chose a wrong answer then the correct answer is displayed in Green in addition.
     * Before proceeding to next question a timer of 5ms is active, so the User can receive visual and
     * audio feedback in the UI.
     *
     * Also saves questioncount for the UI and increments score if answer was correct to given question in
     * the gameController Object instace.
     *
     * @param event click on any Answer Button
     */
    @FXML
    public void answerButton_Clicked(ActionEvent event)  {
        isAnyAnswerButtonClicked = true;
        timeline.stop();
        if(isHandlerActive || isTimeOutBarZero) {
            isTimeOutBarZero = false;
            timeOutBar.setVisible(false);
            closeStageEvent = event;

            // Delays progression to next Question/Stage for a little.
            Timeline delayCleaner = new Timeline(new KeyFrame(Duration.millis(TIMER), event1 -> {
                removeButtonColor();
                currentQuestion++;
                nextQuestion(closeStageEvent);
                isHandlerActive = true;
                handleTimeoutBar();
                if(mediaPlayer != null){
                    mediaPlayer.stop();
                }
            }));

            if(event != null) {
                Button tappedButton = (Button) event.getSource();


                if (tappedButton.getId().equals("answer1Button")) { // checks which button was clicked exactly
                    checkAnswer();
                    pickedAnswer = true;

                    if (pickedAnswer == answer1) { // correct answer path
                        makeButtonGreen(event);
                        gameController.incrementCorrectAnswersCounter();
                    } else { // wrong answer path
                        makeButtonRed(event);
                        showCorrectAnswer();
                    }

                } else if (tappedButton.getId().equals("answer2Button")) {
                    checkAnswer();
                    pickedAnswer = true;

                    if (pickedAnswer == answer2) {
                        makeButtonGreen(event);
                        gameController.incrementCorrectAnswersCounter();
                    } else {
                        makeButtonRed(event);
                        showCorrectAnswer();
                    }
                } else if (tappedButton.getId().equals("answer3Button")) {
                    pickedAnswer = true;
                    checkAnswer();

                    if (pickedAnswer == answer3) {
                        makeButtonGreen(event);
                        gameController.incrementCorrectAnswersCounter();
                    } else {
                        makeButtonRed(event);
                        showCorrectAnswer();
                    }

                } else if (tappedButton.getId().equals("answer4Button")) {
                    pickedAnswer = true;
                    checkAnswer();

                    if (pickedAnswer == answer4) {
                        makeButtonGreen(event);
                        gameController.incrementCorrectAnswersCounter();
                    } else {
                        makeButtonRed(event);
                        showCorrectAnswer();
                    }

                }
            }
            isHandlerActive = false;
            delayCleaner.play();
        }

    }

    /************************* Helper Logic Methods **********************************/


    /**
     * Helper method that checks which Button currently is the correct Answer to the
     * current question
     */
    private void checkAnswer() {
        Question question = questionsList.get(currentQuestion);
        Answer[] answersToQuestion;
        answersToQuestion = question.getAllAnswers();
        for (int i = 0; i < answersToQuestion.length; i++) {
            answersBoolAray[i] = answersToQuestion[i].getIsAnswerCorrect();
        }

        int j = 0;
        answer1 = answersBoolAray[j];
        j++;
        answer2 = answersBoolAray[j];
        j++;
        answer3 = answersBoolAray[j];
        j++;
        answer4 = answersBoolAray[j];
    }

    /**
     * Helper Method that handles the appearance and the logic of the timeoutbar.
     * If timebar is 0, then either a new Question is displayed in the UI or the stage
     * is changed to the Game End Screen.
     */
    private void handleTimeoutBar(){
        if(!isAnyAnswerButtonClicked) {
            timeOutBar.setVisible(true);
            timeOutBar.setStyle("-fx-accent: red");
            timeline = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(timeOutBar.progressProperty(), 1)),
                    new KeyFrame(Duration.seconds(10), e -> {
                        if (!isAnyAnswerButtonClicked) {
                            isTimeOutBarZero = true;
                            timeOutBar.setVisible(false);
                            if((currentQuestion + 1) == questionsList.size()){
                                currentQuestion++;
                                timeline.stop();
                                mediaPlayer.stop();
                                nextQuestion(null);
                            } else if(currentQuestion < (questionsList.size() - 1)) {
                                answerButton_Clicked(null);
                                playAnswerSound(false);
                            }
                        }
                    }, new KeyValue(timeOutBar.progressProperty(), 0))
            );
            timeline.setCycleCount(1);
            timeline.play();
        }
    }

    /**
     * Sets the First Question and their possible Answers in the UI
     * respective to the current question.
     *
     * Starts also the Timeoutbar for the first question.
     */
    public void setQuestions() {

        List<Question> questionList = gameController.getQuestions();

        for (Question question : questionList) {
            questionsList.add(question);
        }

        maxQuestions = questionsList.size();

        // Display first Question and Answer
        if (questionsList != null) { // first question has 0 as index
            String firstQuestion = questionsList.get(currentQuestion).getQuestion();
            String answer1 = questionsList.get(currentQuestion).getAnswerByIndex(0).getAnswer();
            String answer2 = questionsList.get(currentQuestion).getAnswerByIndex(1).getAnswer();
            String answer3 = questionsList.get(currentQuestion).getAnswerByIndex(2).getAnswer();
            String answer4 = questionsList.get(currentQuestion).getAnswerByIndex(3).getAnswer();

            questionLabel.setText(firstQuestion);
            answer1Button.setText(answer1);
            answer2Button.setText(answer2);
            answer3Button.setText(answer3);
            answer4Button.setText(answer4);
            questionIndex.setText("Frage " + (currentQuestion + 1) + " aus " + maxQuestions);
        }
        handleTimeoutBar();
    }

    /**
     * Sets the next Question and their possible Ansewrs in the UI
     * If no next Question is present it closes the window and redirects User to GameEndController.fxml
     *
     * @param event either null if time is up via the timeoutbar or a click on a possible answer
     */
    private void nextQuestion(ActionEvent event) {
        isAnyAnswerButtonClicked = false;
        if (currentQuestion < questionsList.size()) {
            questionLabel.setText(questionsList.get(currentQuestion).getQuestion());
            answer1Button.setText(questionsList.get(currentQuestion).getAnswerByIndex(0).getAnswer());
            answer2Button.setText(questionsList.get(currentQuestion).getAnswerByIndex(1).getAnswer());
            answer3Button.setText(questionsList.get(currentQuestion).getAnswerByIndex(2).getAnswer());
            answer4Button.setText(questionsList.get(currentQuestion).getAnswerByIndex(3).getAnswer());
            updateUi();
        } else {
            Button button;
            Stage primaryStage;
            try{
                if(gameController.getIsGameWon()){
                    primaryStage = (Stage) answer4Button.getScene().getWindow();
                    primaryStage.close();
                    goToWinScreen();
                } else { // Game not won
                    primaryStage = (Stage) answer4Button.getScene().getWindow();
                    primaryStage.close();
                    goToLossScreen();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * This Method proceeds to change the stage to the Loss screen Instance of the gameEndScree
     * @throws IOException is thrown if a FXML resource is not found.
     */
    private void goToLossScreen() throws IOException {
        mediaPlayer.stop();
        timeline.stop();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/gameEnd/gameEndScreen.fxml").openStream());
        GameEndController controller = fxmlLoader.getController();
        controller.setGameController(gameController);
        controller.setGameIsLostUI();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Du hast leider verloren!");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * This Method proceeds to change the stage to the Win screen Instance of the gameEndScree
     * @throws IOException is thrown if a FXML resource is not found.
     */
    private void goToWinScreen() throws IOException {
        timeline.stop();
        mediaPlayer.stop();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/app/gameEnd/gameEndScreen.fxml").openStream());
        GameEndController controller = fxmlLoader.getController();
        controller.setGameController(gameController);
        controller.setGameIsWonUI();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Du hast gewonnen!");
        stage.setResizable(false);
        stage.show();
    }

    /*******************************UI Helper Methods*******************************************/

    /**
     * This Helper Method plays a Sound based on if the chosen Answer to the
     * Question by the user was correct.
     * @param isAnswerCorrect boolean state of the chosen answer by the user
     */
    private void playAnswerSound(boolean isAnswerCorrect) {
        String soundfile;
        if(isAnswerCorrect){
            soundfile = "correctAnswer.wav";
        } else {
            soundfile = "wrongAnswer.mp3";
        }
        Media sound = new Media(getClass().getResource(soundfile).toExternalForm());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }

    /**
     * Helper function that updates Question label when next question is displayed
     */
    private void updateUi() {
        questionIndex.setText("Frage " + (currentQuestion + 1) + " aus " + maxQuestions);
    }

    /**
     * Helper function that makes a specific Button the user clicked Green
     * Should be used only for Buttons that have correct Answer to given Question.
     *
     * @param event click on the chosen Answer of the User if it was correct
     */
    private void makeButtonGreen(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.getStyleClass().add("correctAnswer");
        playAnswerSound(true);
    }

    /**
     * Helper function that makes a specific Button the user clicked Red
     * Should be used only for Buttons that have wrong Answer to given Question
     *
     * @param event click on the chosen Answer of the User if it was wrong
     */
    private void makeButtonRed(ActionEvent event) {
        Button button = (Button) event.getSource();
        button.getStyleClass().add("wrongAnswer");
        playAnswerSound(false);
    }

    /**
     * Helper method that turns in the UI the button with the correct answer green
     * respective to the current question
     */
    private void showCorrectAnswer() {
        int i = 0;
        for (; i < answersBoolAray.length; i++) {
            if (answersBoolAray[i] == true)
                break;
        }
        switch (i) {
            case 0:
                answer1Button.getStyleClass().add("correctAnswer");
                break;

            case 1:
                answer2Button.getStyleClass().add("correctAnswer");
                break;

            case 2:
                answer3Button.getStyleClass().add("correctAnswer");
                break;

            default: // Button 4
                answer4Button.getStyleClass().add("correctAnswer");
        }
    }

    /**
     * Helper function that resets all Button Colors to original color
     *
     */
    private void removeButtonColor() {
        answer1Button.getStyleClass().removeAll("wrongAnswer");
        answer1Button.getStyleClass().removeAll("correctAnswer");

        answer2Button.getStyleClass().removeAll("wrongAnswer");
        answer2Button.getStyleClass().removeAll("correctAnswer");

        answer3Button.getStyleClass().removeAll("wrongAnswer");
        answer3Button.getStyleClass().removeAll("correctAnswer");

        answer4Button.getStyleClass().removeAll("wrongAnswer");
        answer4Button.getStyleClass().removeAll("correctAnswer");
    }
}