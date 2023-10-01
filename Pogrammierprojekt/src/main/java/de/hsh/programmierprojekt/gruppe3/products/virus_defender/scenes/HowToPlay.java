package de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes;

import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Background;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomButton;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomEventHandler;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.CustomLabel;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * Klasse für die HTP Szene/Stage.
 *
 */
public class HowToPlay {
    Stage window;
    Background background;

    CustomLabel customLabel;
    CustomButton closeButton;
    CustomEventHandler customEvent;
    Scene sceneHTP;

    /**
     * Neues Fenster (stage) wird erstellt.
     */
    public void display()  {
        window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("VIRUS DEFENDER: HOW TO PLAY");
        background = new Background("/virus_defender_res/image/HTPBlackBackground.jpg");
        customEvent = new CustomEventHandler();
        customLabel = new CustomLabel("HOW TO PLAY", 22, 220, 0);



        Text t = new Text();
        t.setCache(true);
        t.setX(50);
        t.setY(100);
        t.setFill(Color.VIOLET);
        t.setText("Es gibt 6 Schwierigkeitsgrade. \r\n"
                + "Man hat immer drei Sekunden bis ein Virus \"explodiert\" und man eines\r\n"
                + "von drei Leben verliert. Das Spiel geht solange man noch Leben hat.\r\n"
                + "Die Schwierigkeitsgrade unterscheiden sich, dabei das\r\n"
                + "die Viren unterschiedlich schnell erzeugt werden.\r\n"
                + "Mit ESC beendet man das laufende Spiel und geht zum Mainmenu.\r\n"
                + "Very Easy = 1.25\r\n"
                + "Easy = 1\r\n"
                + "Normal = 0.8\r\n"
                + "Hard = 0.65\r\n"
                + "Very Hard = 0.5\r\n"
                + "Hell = 0.3\r\n"
                + "");
        t.setFont(Font.font(null, FontWeight.BOLD, 16));


        closeButton = new CustomButton("Close", 240, 300);
        closeButton.getButton().setOnAction(e -> window.close());

        Pane popUpWindow = new Pane();
        popUpWindow.getChildren().addAll(background.getBackground(),t, customLabel.getCustomLabel(), closeButton.getButton());
        sceneHTP = new Scene(popUpWindow, 600, 400);

        window.setResizable(false);
        window.setScene(sceneHTP);
        window.showAndWait();

    }


}