package de.hsh.programmierprojekt.gruppe3.products.nonogramm;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.GUI_Kontrolle;

public class Nonogramm {
	public static void main(String[] args) {
		launchNonogramm();
	}

	public static void launchNonogramm() {
		GUI_Kontrolle kontrolle = new GUI_Kontrolle();
		kontrolle.changeToFrameOne();
	}
}
