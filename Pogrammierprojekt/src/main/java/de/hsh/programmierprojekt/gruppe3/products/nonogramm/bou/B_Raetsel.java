package de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou;

import java.util.ArrayList;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.app.C_Raetsel;

public class B_Raetsel {
	C_Raetsel schnittstelle = new C_Raetsel();

	public int getSize() {
		return schnittstelle.getSize();
	}

	public ArrayList<Integer> getNummerOfReihe(int reiheID) {
		return schnittstelle.getNummerOfReihe(reiheID);
	}

	public ArrayList<Integer> getNummerOfSpalte(int spalteID) {
		return schnittstelle.getNummerOfSpalte(spalteID);
	}

	public int getMaxSizeReihe() {
		return schnittstelle.getMaxSizeReihe();
	}

	public int getMaxSizeSpalte() {
		return schnittstelle.getMaxSizeSpalte();
	}

	public boolean getCheckOnReihe(int reiheID) {
		return schnittstelle.getCheckOnReihe(reiheID);
	}

	public boolean getCheckOnSpalte(int spalteID) {
		return schnittstelle.getCheckOnSpalte(spalteID);
	}

}
