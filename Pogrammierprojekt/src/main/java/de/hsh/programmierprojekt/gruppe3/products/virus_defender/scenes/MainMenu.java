package de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes;

import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Background;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomButton;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomEventHandler;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomLabel;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 *
 * Klasse für die Mainmenu Szene.
 *
 */
public class MainMenu {
    Stage window;
    Scene hauptMenu;
    CustomLabel customLabel;
    CustomEventHandler customEvent;
    Pane mainMenu;
    Background background;
    CustomButton startButton, htpButton, exitButton, backButton;
    /**
     * Hier wird auch die Stage definiert.
     *Title, Buttons sowie deren Aktione werden hier initialisiert
     * und in eine Szene gepackt.
     * @param stage
     */
    public MainMenu(Stage stage) {
        window = stage;
        customEvent = new CustomEventHandler();
        customLabel = new CustomLabel("WELCOME TO VIRUS DEFENDER", 22, 132, 100);
        mainMenu = new Pane();
        background = new Background("/virus_defender_res/image/testbackgroundsize.jpg");


        startButton = new CustomButton("START", 240, 220);
        htpButton = new CustomButton("HOW TO PLAY", 200, 300);
        exitButton = new CustomButton("EXIT", 240, 380);
        backButton = new CustomButton("Back",  450, 520);

        startButton.getButton().setOnAction(e -> customEvent.levelSelectionE(window));
        htpButton.getButton().setOnAction(e -> customEvent.howToPlayE());
        exitButton.getButton().setOnAction(e -> {
            try {
                customEvent.backToSpielesammlung(window);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        mainMenu.getChildren().addAll(background.getBackground(), customLabel.getCustomLabel() ,startButton.getButton(),
                htpButton.getButton(), exitButton.getButton());
        hauptMenu = new Scene(mainMenu, 600, 600);

        window.setResizable(false);
        window.setScene(hauptMenu);
        window.setTitle("VIRUS DEFENDER");
        window.show();
    }

    /**
     *
     * @return hauptmenu Szene wird zurückgegeben.
     */
    public Scene getMainMenu() {
        return hauptMenu;
    }


}