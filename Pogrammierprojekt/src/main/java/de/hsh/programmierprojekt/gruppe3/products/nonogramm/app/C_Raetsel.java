package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

import java.util.ArrayList;

public class C_Raetsel {
	public int getSize() {
		return C_RaetselKontrolle.getRaetsel().getSize();
	}

	public ArrayList<Integer> getNummerOfReihe(int reiheID) {
		return C_RaetselKontrolle.getRaetsel().getNummerListe().getNummerOfReihe(reiheID);
	}

	public ArrayList<Integer> getNummerOfSpalte(int spalteID) {
		return C_RaetselKontrolle.getRaetsel().getNummerListe().getNummerOfSpalte(spalteID);
	}
	
	public int getMaxSizeReihe() {
		int maxSizeReihe = 0;
		for (int i = 0; i < this.getSize(); i++) {
			int newSize = this.getNummerOfReihe(i).size();
			if (newSize > maxSizeReihe) {
				maxSizeReihe = newSize;
			}
		}
		return maxSizeReihe;
	}
	
	public int getMaxSizeSpalte() {
		int maxSizeSpalte = 0;
		for (int i = 0; i < this.getSize(); i++) {
			int newSize = this.getNummerOfSpalte(i).size();
			if (newSize > maxSizeSpalte) {
				maxSizeSpalte = newSize;
			}
		}
		return maxSizeSpalte;
	}

	public boolean getCheckOnReihe(int reiheID) {
		return C_RaetselKontrolle.getRaetsel().getPrueferListe().getCheckOnReihe(reiheID);
	}

	public boolean getCheckOnSpalte(int spalteID) {
		return C_RaetselKontrolle.getRaetsel().getPrueferListe().getCheckOnSpalte(spalteID);
	}

}
