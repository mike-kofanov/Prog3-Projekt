package de.hsh.programmierprojekt.gruppe3.products.virus_defender.entity;

import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.util.Objects;

/**
 *
 * Hier wird der Virus Objekt erstellt.
 *
 */
class Virus extends StackPane {

    Image image;


    boolean clicked;
    /**
     * Radius und die Koordinaten des Virus werden festgelegt.
     * auch kriegt er hier sein design.
     */
    public Virus() {
        double x = Math.floor(Math.random() * ((550 - 50) + 1) + 50);
        double y = Math.floor(Math.random() * ((550 - 50) + 1) + 50);
        clicked = false;
        setLayoutX(x);
        setLayoutY(y);

        double r = Math.floor(Math.random() * ((30 - 10) + 1) + 10);
        image = new Image (Objects.requireNonNull(getClass().getResource("/virus_defender_res/image/Virus1RED.png")).toString());
        Circle virus = new Circle(
                r,
                Color.VIOLET
        );


        virus.setFill(new ImagePattern(image));
        getChildren().setAll(
                virus

        );
    }
    /**
     * Brauchen wir für die Player Klasse,
     * kriegt ein boolean werd und ändert clicked auf diesen Wert.
     *
     */
    public void changeClicked(boolean bool) {
        this.clicked = bool;
    }
    /**
     *  Brauchen wir für die Player Klasse,
     * @return gibt den wert von clicked wieder.
     */
    public boolean getClicked() {
        return this.clicked;
    }



}