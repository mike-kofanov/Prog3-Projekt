package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GUI_Sound {
	public void playClick() {
		playSound("/nonogramm/sound/Click.wav");
	}
	
	public void playWin() {
		playSound("/nonogramm/sound/Win.wav");		
	}
	private void playSound(String file) {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(getClass().getResource(file));
			Clip clip = AudioSystem.getClip();

			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
