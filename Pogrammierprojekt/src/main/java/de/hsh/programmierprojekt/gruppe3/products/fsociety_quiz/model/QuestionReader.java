package de.hsh.programmierprojekt.gruppe3.products.fsociety_quiz.model;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

/**
 * This class reads the questions for the
 * quiz from an external text file and prepares them
 * for the quiz.
 * @author Mike Kofanov
 */
public class QuestionReader {

    /**
     * This Method reads Questions and its answers from an external text File
     * and returns the Question as an internal OOP representation for the Quiz.
     * @return Questions as a List
     */
    public List<Question> readQuestions() {
        List<Question> questionList = new LinkedList<>();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = getClass().getResourceAsStream("/de/hsh/programmierprojekt/gruppe3/products/fsociety_quiz/data/questions.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            do {
                line = reader.readLine();
                if (line != null){
                    String[] questionArray = line.split(";");
                    questionList.add(new Question(questionArray[0],
                            new Answer(questionArray[1], Boolean.getBoolean(questionArray[2])),
                            new Answer(questionArray[3], Boolean.getBoolean(questionArray[4])),
                            new Answer(questionArray[5], Boolean.getBoolean(questionArray[6])),
                            new Answer(questionArray[7], Boolean.getBoolean(questionArray[8]))
                    ));
                }
            } while (line != null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                try {
                    reader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return questionList;
    }
}
