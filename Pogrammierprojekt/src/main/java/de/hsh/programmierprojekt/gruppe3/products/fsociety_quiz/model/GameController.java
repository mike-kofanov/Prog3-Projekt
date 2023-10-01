package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class represents all Data for the current Session of the Fsociety Game
 * @author Mike Kofanov
 */
public class GameController {

    /** Internal value for the current Difficulty Setting of the session */
    private final double difficultySetting;
    /** All Questions that will be displayed in the current game session of Fsociety */
    private List<Question> questions;
    /** Ammount of correctAnswers in the current Fsociety Game session */
    private int correctAnswersCounter;
    /** Status is the game is Won at the current game state for this session */
    private boolean isGameWon;

    /**
     * Constructor for an instance of the GameController Object
     * @param difficultySetting Diffuclty Setting chosen by the user for the current session
     */
    public GameController(Difficulty difficultySetting) {
        this.difficultySetting = difficultySetting.percentage;
        this.questions = new QuestionReader().readQuestions();
        this.correctAnswersCounter = 0;
    }

    /**
     * Getter for all Question for the current game session
     * @return Questions as a List
     */
    public List<Question> getQuestions(){
        return this.questions;
    }

    /**
     * Enum that represents possible Difficulties for the current session, to achieve a win
     * EASY - 30% or more answers correct
     * MEDIUM - 50% or more answers correct
     * HARD - 75% or more answers correct
     */
    public enum Difficulty {
        EASY(30.0), MEDIUM(50.0), HARD(75.0);
        public final double percentage;
        private Difficulty(double percentage){
            this.percentage = percentage;
        }
    }

    /**
     * Internal Method that checks if the game is Won at the current State.
     */
    private void checkIfGameIsWon(){
        double percentageOfCorrectAnswers = (double) correctAnswersCounter * 100.0 / (double) questions.size();
        isGameWon = percentageOfCorrectAnswers >= difficultySetting;
    }

    /**
     * Method that increments the amount of Correct answers by 1
     */
    public void incrementCorrectAnswersCounter(){
        this.correctAnswersCounter++;
    }

    /**
     * Getter Method that gets the total amount of current correct answers
     * @return correct answers amount for the current game state
     */
    public int getCorrectAnswersCounter() {
        return correctAnswersCounter;
    }

    /**
     * This Method checks if the game is won under current gamestate
     * and returns that as a boolean.
     * @return isGameWon
     */
    public boolean getIsGameWon(){
        checkIfGameIsWon();
        return this.isGameWon;
    }
}
