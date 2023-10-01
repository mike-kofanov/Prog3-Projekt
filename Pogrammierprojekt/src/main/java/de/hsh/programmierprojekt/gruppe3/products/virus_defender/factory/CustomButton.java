package de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory;

import javafx.scene.control.Button;
/**
 * Die Klasse ist zu erstellen von Buttons da.
 *
 */
public class CustomButton {
    private final Button augmentButton;
    /**
     *
     * @param buttontext, der Name des buttons
     * @param x, x Koordinate
     * @param y, y Koordinate
     */
    public CustomButton(String buttontext, double x, double y) {
        augmentButton = new Button (buttontext);
        augmentButton.setLayoutX(x);
        augmentButton.setLayoutY(y);
        augmentButton.setStyle("-fx-font-size:25");
        augmentButton.setMinSize(120, 50);

    }
    /**
     *
     * @return den erstellten Button
     */
    public Button getButton() {
        return augmentButton;
    }
}