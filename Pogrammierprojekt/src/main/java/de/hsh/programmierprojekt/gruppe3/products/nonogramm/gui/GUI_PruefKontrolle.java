package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.PruefListener;

public class GUI_PruefKontrolle implements PruefListener {
	private GUI_Kontrolle gui_Kontrolle;

	public GUI_PruefKontrolle(GUI_Kontrolle gui_Kontrolle) {
		this.gui_Kontrolle = gui_Kontrolle;
	}

	@Override
	public void reiheChangedEvent(int reiheID) {
		gui_Kontrolle.setKorrektReihe(reiheID);
	}

	@Override
	public void spalteChangedEvent(int spalteID) {
		gui_Kontrolle.setKorrektSpalte(spalteID);
	}

	@Override
	public void allCorrectEvent() {
		gui_Kontrolle.raetselWon();
	}

}
