package de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory;

import de.hsh.programmierprojekt.gruppe3.core.MainMenuController;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.entity.Player;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.HowToPlay;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.Ingame;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.LevelSelection;
import de.hsh.programmierprojekt.gruppe3.products.virus_defender.scenes.MainMenu;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Die Klasse ist für fast alle Aktione der Button zuständig.
 *
 */
public class CustomEventHandler {
    HowToPlay htp;
    LevelSelection levelSelection;
    Ingame ingame;
    Player virusSP;
    MainMenu mainMenu;

    /**
     * der Konstruktor initialisiert hier nur die Player Klasse,
     */
    public CustomEventHandler( ){

        virusSP = new Player();
    }
    /**
     *
     * Starte das Spiel in Very Easy.
     */
    public void vEasyButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(1.25);
    }
    /**
     *
     * Starte das Spiel in Easy.
     */
    public void easyButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(1);
    }
    /**
     *
     * Starte das Spiel in Normal.
     */
    public void normalButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(0.8);
    }
    /**
     *
     * Starte das Spiel in Hard.
     */
    public void hardButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(0.65);
    }
    /**
     *
     * Starte das Spiel in Very Hard.
     */
    public void vHardButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(0.5);
    }
    /**
     *
     * Starte das Spiel in Hell.
     */
    public void hellButtonE(Stage window) {

        ingame = new Ingame(window, virusSP);
        window.setScene(ingame.getIngame());
        virusSP.virusG(0.3);
    }
    /**
     *
     *kehrt zum Hauptmenu zurück.
     */
    public void closeButtonE(Stage window) {

        mainMenu = new MainMenu(window);
        window.setScene(mainMenu.getMainMenu());
    }
    /**
     *
     * Scene für LevelSelection wird gestartet.
     */
    public void levelSelectionE(Stage window) {

        levelSelection = new LevelSelection( window);
        window.setScene(levelSelection.getLevelSelection());
    }
    /**
     * Neues Fenster für HTP wird gestartet.
     */
    public void howToPlayE() {

        htp = new HowToPlay();
        htp.display();
    }

    /**
     *  Die Methode schließt die Stage für das Spiel und
     *  erschaft eine neue Stage für die Spielesammlung.
     */
    public void backToSpielesammlung(Stage primaryStage) throws IOException {
        primaryStage.close();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/core/mainMenu.fxml").openStream());
        MainMenuController controller = fxmlLoader.getController();
        controller.setBackgroundImages();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Spielesammlung Dark IT");
        stage.setResizable(false);
        stage.show();
    }
}
