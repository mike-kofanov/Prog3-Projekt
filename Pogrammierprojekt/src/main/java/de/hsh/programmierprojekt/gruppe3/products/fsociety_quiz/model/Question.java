package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model;

/**
 * This class represents a Question with four possible Answers.
 * @author Mike Kofanov
 */
public class Question {
    /** Question as a String */
    private final String question;
    /** Array of possible Answers to the Question */
    private final Answer[] answers = new Answer[4];

    /**
     * Constructor to the Question Object
     * One answer to the Question must be true!
     * @param question Question
     * @param answer1 first answer and its boolean value
     * @param answer2 second answer and its boolean value
     * @param answer3 third answer and its boolean value
     * @param answer4 fourth answer and its boolean value
     */
    public Question(String question, Answer answer1, Answer answer2, Answer answer3, Answer answer4) {
        this.question = question;
        this.answers[0] = answer1;
        this.answers[1] = answer2;
        this.answers[2] = answer3;
        this.answers[3] = answer4;
    }

    /**
     * Getter for the actual Question
     * @return question as a String
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Getter for all possible Answers to the Question
     * @return Array with Answers to the Question
     */
    public Answer[] getAllAnswers() {
        return answers;
    }

    /**
     * Getter for a specific Answer to Question
     * @param i index of the Answer
     * @return Answer Object
     */
    public Answer getAnswerByIndex(int i){
        return this.answers[i];
    }
}
