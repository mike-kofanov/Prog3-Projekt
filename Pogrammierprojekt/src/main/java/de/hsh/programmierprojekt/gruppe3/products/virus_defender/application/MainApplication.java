package de.hsh.programmierprojekt.gruppe3.products.virus_defender.application;



import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage primarystage) {

        try {

            MainMenu mainmenu = new MainMenu(primarystage);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
