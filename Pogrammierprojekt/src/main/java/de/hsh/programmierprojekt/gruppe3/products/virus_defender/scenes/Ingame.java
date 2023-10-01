package de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes;


import de.hsh.programmierprojekt.gruppe3.products.virus_defender.entity.Player;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Background;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * Klasse für die Ingame Szene.
 *
 */

public class Ingame {
    Scene ingame;
    Stage window;
    Background background, backgroundEG;
    MainMenu mainmenu;
    Player v;
    /**
     * Hier wird alles von der Player Klasse in eine Szene gepackt.
     * @param stage wird übergeben.
     * @param virusSP Player Klasse wird übergeben.
     */
    public   Ingame(Stage stage, Player virusSP) {
        window = stage;
        v = virusSP;


        backgroundEG = new Background("/virus_defender_res/image/EasterEGG.jpg");
        background = new Background("/virus_defender_res/image/goodBackground.jpg");
        backgroundEG.getBackground().setOpacity(0);

        Pane root = new StackPane(  backgroundEG.getBackground(),background.getBackground(), v.getVirusField(),
                v.createScoreOverlay(),
                v.createLebenOverlay());
        ingame = new Scene(root, 600, 600);

        // Listen damit die Szene gewechselt wird, wenn Leben auf 0 fällt.
        v.getLeben().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(v.leben.get()==0) {
                    int result = v.score.get();
                    PostGame ps = new PostGame(window, result);
                    window.setScene(ps.getPostGame());
                }

            }
        });
		 /* Key Aktione. Einmal, damit man mit ESC zurück zum Hauptmenü kommt
		  	und der andere für den Easter Egg.
		 */
        ingame.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent k) {
                if (k.getCode().equals(KeyCode.ESCAPE)) {
                    mainmenu = new MainMenu(window);
                    v.leben.set(100);
                    window.setScene(mainmenu.getMainMenu());
                    v.stopVirusG();
                } else if(k.getCode().equals(KeyCode.H)) {
                    background.getBackground().setOpacity(0);
                    backgroundEG.getBackground().setOpacity(1);
                }
            }
        });

    }
    /**
     *
     * @return ingame scene.
     */
    public Scene getIngame() {
        return ingame;
    }

}
