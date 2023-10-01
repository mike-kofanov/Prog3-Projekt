package de.hsh.programmierprojekt.gruppe3.products.virus_defender.factory;

import javafx.scene.media.AudioClip;


import java.util.Objects;

/**
 * Klasse für Sound.
 *
 *
 */
public class Sound {
    AudioClip soundEffect;
    public Sound (String clickA, double a) {

        soundEffect = new AudioClip(Objects.requireNonNull(getClass().getResource(clickA)).toString());

        soundEffect.setVolume(a);
    }

    /**
     *
     * @return gibt den Sound wieder.
     */
    public AudioClip getSoundEffect() {
        return soundEffect;
    }


}