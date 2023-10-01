package de.hsh.programmierprojekt.gruppe3.products.virus_defender.entity;


import de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory.Sound;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;


/**
 * In dieser Klasse ist das Spiel.
 * Hier wird Score und Leben initialisiert.
 * Auch ist die Timeline, welcher rate die
 * Viren auftauchen und wie diese wieder verschwinden, alles in dieser Klasse.
 */

public class Player {


    public final IntegerProperty score;
    final Pane virusField;
    public final IntegerProperty leben;
    Timeline virusGenerator;
    Sound click, notClick;

    /**
     * leben, score und Sound werden initialisiert.
     * Ein neues Pane wird gemacht.
     */
    public Player() {

        click = new Sound("/virus_defender_res/sound/Click.wav", 0.015);
        notClick = new Sound("/virus_defender_res/sound/NotClick.wav", 0.015);

        leben = new SimpleIntegerProperty(3);
        score = new SimpleIntegerProperty(0);

        virusField = new Pane();

    }

    /**
     *
     * @return das aktuelle Leben.
     */
    public IntegerProperty getLeben() {
        return this.leben;
    }
    /**
     * Erstellt die Timeline, damit die Viren spawnen können.
     * @param d, die geschwindigkeit in der die Viren spawnen sollen wird übergeben.
     */
    public void virusG(double d) {
        virusGenerator = new Timeline(
                new KeyFrame(Duration.millis(1), event -> checkDeath()),
                new KeyFrame(Duration.seconds(d), event -> spawnVirus())


        );
        virusGenerator.setCycleCount(Timeline.INDEFINITE);

        virusGenerator.play();

    }
    /**
     *
     * @return den Pane virusField.
     */
    public Pane getVirusField() {
        return virusField;
    }

    public void checkDeath() {
        if(this.leben.get() == 0) {
            virusGenerator.stop();
        }

    }
    /**
     * ist zum Stoppen der Timeline.
     */
    public void stopVirusG() {
        virusGenerator.stop();
    }

    /**
     * Erstellt ein label für Score.
     * @return scoreOverly
     */
    public StackPane createScoreOverlay() {
        Label totalScoreLabel = new Label();
        totalScoreLabel.setTextFill(Color.ORANGE);
        totalScoreLabel.setFont(Font.font("FontT", FontWeight.BOLD,18));
        totalScoreLabel.textProperty().bind(
                Bindings.concat(
                        "Score: ", score.asString()
                )
        );

        StackPane scoreOverlay = new StackPane(totalScoreLabel);
        StackPane.setAlignment(totalScoreLabel, Pos.TOP_LEFT);
        scoreOverlay.setMouseTransparent(true);

        return scoreOverlay;
    }



    /**
     * Erstellt ein Label für Leben.
     * @return lebenOverlay
     */
    public StackPane createLebenOverlay() {
        Label totalLebenLabel = new Label();
        totalLebenLabel.setTextFill(Color.ORANGE);
        totalLebenLabel.setFont(Font.font("FontT", FontWeight.BOLD,18));
        totalLebenLabel.textProperty().bind(
                Bindings.concat(
                        "Leben: ", leben.asString()
                )
        );

        StackPane LebenOverlay = new StackPane(totalLebenLabel);
        StackPane.setAlignment(totalLebenLabel, Pos.TOP_RIGHT);
        LebenOverlay.setMouseTransparent(true);

        return LebenOverlay;
    }



    /**
     * Ruft Virus Klasse auf, wenn leben ungleich 0 ist.
     * Der virus verschwinden nach dre sekunden oder man klicked es
     * an, damit es verschwindet.
     *
     */
    public void spawnVirus() {
        if(this.leben.get() != 0) {
            Virus virus = new Virus();

            virus.setOnMouseClicked(
                    event -> {
                        click.getSoundEffect().play();
                        virus.changeClicked(true);
                        score.set(score.get() + 1);
                        virusField.getChildren().remove(virus);
                    }
            );
            FadeTransition fade = new FadeTransition(Duration.seconds(3),virus);
            fade.setFromValue(1);
            fade.setToValue((0));
            fade.setOnFinished( event -> {
                virusField.getChildren().remove(virus);

                if(!virus.getClicked()) {
                    if (leben.get() > 0 && leben.get() < 4 ) {
                        notClick.getSoundEffect().play();
                        leben.set(leben.get() - 1);

                    }
                }
            });
            fade.play();
            virus.changeClicked(false);
            virusField.getChildren().add(virus);
        }
    }

}