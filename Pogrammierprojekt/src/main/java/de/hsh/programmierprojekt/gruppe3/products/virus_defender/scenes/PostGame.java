package de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes;

import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Background;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomButton;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomEventHandler;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomLabel;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * Klasse für Post Game Szene.
 *
 */
public class PostGame {
    Stage window;
    Scene scenePG;
    CustomButton backButton;
    Background background;
    CustomEventHandler customEvent;
    CustomLabel customLabel ;
    int endScore;
    /**
     *
     * @param stage wird übergeben.
     * @param result die erreichte score wrid übergeben.
     */
    public PostGame(Stage stage, int result) {
        window = stage;
        endScore = result;
        customLabel = new CustomLabel("SCORE "+endScore, 30,230, 260);
        Pane layout = new Pane();
        customEvent = new CustomEventHandler();
        background = new Background("/virus_defender_res/image/posBackground4.jpg");
        backButton = new CustomButton("Return to Mainmenu", 150, 450);
        backButton.getButton().setOnAction(e -> customEvent.closeButtonE(window));

        layout.getChildren().addAll(background.getBackground(),customLabel.getCustomLabel(), backButton.getButton());
        scenePG = new Scene(layout, 600, 600);

    }
    /**
     *
     * @return PostGame scene.
     */
    public Scene getPostGame() {

        return scenePG;
    }



}