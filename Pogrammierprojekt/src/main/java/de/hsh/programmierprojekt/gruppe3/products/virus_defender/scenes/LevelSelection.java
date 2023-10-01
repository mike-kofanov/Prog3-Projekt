package de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes;

import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Background;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomButton;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomEventHandler;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * Klasse für die LevelSelection Szene.
 *
 */
public class LevelSelection {

    Stage window;
    Scene sceneLS;
    Background background;
    CustomEventHandler customEvent;
    CustomButton vEasyButton, easyButton, normalButton,
            hardButton, vHardButton, hellButton, backButton;
    /**
     * Title, Buttons sowie deren Aktione werden hier initialisiert
     *  und in eine Szene gepackt.
     * @param stage
     */
    public LevelSelection( Stage stage) {
        window = stage;
        customEvent = new CustomEventHandler();
        background = new Background("/virus_defender_res/image/LSBackground.jpg");



        backButton = new CustomButton("Back",  450, 520);
        vEasyButton = new CustomButton("Very Easy", 150, 220);
        easyButton = new CustomButton("Easy", 330, 220);
        normalButton = new CustomButton("Normal", 150, 300);
        hardButton = new CustomButton("Hard", 330, 300);
        vHardButton = new CustomButton("Very Hard", 150, 380);
        hellButton = new CustomButton("Hell", 330, 380);



        vEasyButton.getButton().setOnAction(e -> customEvent.vEasyButtonE(window));
        easyButton.getButton().setOnAction(e -> customEvent.easyButtonE(window));
        normalButton.getButton().setOnAction(e -> customEvent.normalButtonE(window));
        hardButton.getButton().setOnAction(e -> customEvent.hardButtonE(window));
        vHardButton.getButton().setOnAction(e -> customEvent.vHardButtonE(window));
        hellButton.getButton().setOnAction(e -> customEvent.hellButtonE(window));
        backButton.getButton().setOnAction(e -> customEvent.closeButtonE(window));




        Pane layoutLS = new Pane();
        layoutLS.getChildren().addAll(background.getBackground(), vEasyButton.getButton(), easyButton.getButton(),
                normalButton.getButton(), hardButton.getButton(),
                vHardButton.getButton(),hellButton.getButton(), backButton.getButton());
        sceneLS = new Scene(layoutLS, 600, 600);


    }
    /**
     *
     * @return sceneLs.
     */
    public Scene getLevelSelection() {
        return sceneLS;
    }



}