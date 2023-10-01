package de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
/**
 * Klasse um Label zu erstellen.
 */
public class CustomLabel {

    Label title;
    /**
     * Erstellt beim inizialisieren den gewünschten Label.
     * @param name Label Name
     * @param g Font
     * @param x, x Koordinate
     * @param y, y Koordinate
     */
    public CustomLabel(String name,int g, int x, int y) {

        title= new Label(name);
        title.setTextFill(Color.VIOLET);
        title.setFont(Font.font("FontT", FontWeight.BOLD,g));
        title.setLayoutX(x);
        title.setLayoutY(y);


    }
    /**
     *
     * @return gibt den label zurück.
     */
    public Label getCustomLabel() {

        return title;
    }

}