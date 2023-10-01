package de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;


/**
 * Die Klaase ist dazu da die jeweiligen Hintergrundbilder zu laden.
 *
 */
public class Background {
    Image image;
    ImageView background;
    /**
     *
     * @param imagePath, der  Speicherort des Hintergrundbildes wird als
     * String dem Konstruktor übergeben.
     *
     */
    public Background (String imagePath) {
        image = new Image(Objects.requireNonNull(getClass().getResource(imagePath)).toString());
        background = new ImageView(image);
    }
    /**
     *
     * @return ImageView wird zurückgegeben.
     */
    public ImageView getBackground() {
        return background;
    }

}
