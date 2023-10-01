package de.hsh.programmierprojekt.gruppe3.products.nonogramm.per;

public class PrueferListe {

	private int checks;
	private final int checksTotal;
	private boolean[] checkReihe;
	private boolean[] checkSpalte;

	public PrueferListe(int size) {
		checks = 0;
		checksTotal = 2 * size;

		checkReihe = new boolean[size];
		for (int i = 0; i < size; i++) {
			checkReihe[i] = false;
		}

		checkSpalte = new boolean[size];
		for (int i = 0; i < size; i++) {
			checkSpalte[i] = false;
		}
	}

	public void setChecks(int checks) {
		this.checks = checks;
	}

	public int getChecks() {
		return checks;
	}

	public int getChecksTotal() {
		return checksTotal;
	}

	public void setCheckOnReihe(int reiheID, boolean wert) {
		checkReihe[reiheID] = wert;
	}

	public boolean getCheckOnReihe(int reiheID) {
		return checkReihe[reiheID];
	}

	public void setCheckOnSpalte(int spalteID, boolean wert) {
		checkSpalte[spalteID] = wert;
	}

	public boolean getCheckOnSpalte(int spalteID) {
		return checkSpalte[spalteID];
	}
}
