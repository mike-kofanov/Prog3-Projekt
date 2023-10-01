package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model;

/**
 * This class represents the properties of an Answer to a Question
 * @author Mike Kofanov
 */
public class Answer {
    /** statement */
    private final String answer;
    /** boolean value of the statement */
    private final boolean isAnswerCorrect;

    /**
     * Constructor for an Object of the Answer class
     * @param answer
     * @param isAnswerCorrect
     */
    public Answer(String answer, boolean isAnswerCorrect) {
        this.answer = answer;
        this.isAnswerCorrect = isAnswerCorrect;
    }

    /**
     * Getter for statement of an Answer
     * @return statement to a Question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Getter for the boolean value of the statement of an Answer
     * @return boolean value of the Statement to the Question
     */
    public boolean getIsAnswerCorrect() {
        return isAnswerCorrect;
    }
}
