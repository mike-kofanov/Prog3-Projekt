package de.hsh.programmierprojekt.gruppe3.products.snake.util;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    Clip clip;
    URL soundUrl[] = new URL[30];
    
    public Sound() {
        
        
    }

    public static void playSound2() {
        play(Sound.class.getResource("sound/Background.av.wav"), true);
    }

    public static void playSound1() {
        play(Sound.class.getResource("sound/eating1.wav"), false);
    }

    private static void play(URL soundUrl, boolean loop) {
        try {
            final Clip clip = AudioSystem.getClip();
            final AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundUrl);
            clip.open(inputStream);
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                clip.start();
            }
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            System.err.println("Fehler bei Soundausgabe");
            e.printStackTrace();
        }
    }
}
