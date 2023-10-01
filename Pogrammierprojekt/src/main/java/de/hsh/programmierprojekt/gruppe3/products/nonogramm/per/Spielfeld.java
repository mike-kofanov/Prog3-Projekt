package de.hsh.programmierprojekt.gruppe3.products.nonogramm.per;

public class Spielfeld {
	private Feld[][] felder;

	public Spielfeld(int size) {
		felder = new Feld[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				felder[i][j] = Feld.LEER;
			}
		}
	}

	public Feld[] getReihe(int reiheID) {
		return felder[reiheID];
	}

	public Feld[] getSpalte(int spalteID) {
		Feld[] spalte = new Feld[felder.length];
		for (int i = 0; i < felder.length; i++) {
			spalte[i] = felder[i][spalteID];
		}
		return spalte;
	}

	public Feld getFeld(int reiheID, int spalteID) {
		return felder[reiheID][spalteID];
	}

	public void setFeld(int reiheID, int spalteID, Feld neuFeld) {
		felder[reiheID][spalteID] = neuFeld;
	}
}
