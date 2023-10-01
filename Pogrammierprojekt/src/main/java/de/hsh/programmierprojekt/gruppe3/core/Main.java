package de.hsh.programmierprojekt.gruppe3.core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main Klasse
 * Startet die Spielesammlung Dark IT
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent root = fxmlLoader.load();
        MainMenuController controller = fxmlLoader.getController();
        controller.setBackgroundImages();
        primaryStage.setTitle("Spielesammlung Dark IT");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
