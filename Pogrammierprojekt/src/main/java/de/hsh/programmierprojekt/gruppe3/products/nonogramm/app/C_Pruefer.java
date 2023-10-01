package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

import java.util.ArrayList;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.PruefListener;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.Feld;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.PrueferListe;

public class C_Pruefer {
	private PruefListener listener;

	private void checkReihe(int reiheID) {
		Feld[] reihe = C_RaetselKontrolle.getRaetsel().getSpielfeld().getReihe(reiheID);
		ArrayList<Integer> reiheNummer = C_RaetselKontrolle.getRaetsel().getNummerListe().getNummerOfReihe(reiheID);
		PrueferListe prueferListe = C_RaetselKontrolle.getRaetsel().getPrueferListe();

		boolean newBool = checkIfKorrekt(reihe, reiheNummer);
		boolean oldBool = prueferListe.getCheckOnReihe(reiheID);
		if (!oldBool && newBool) {
			prueferListe.setCheckOnReihe(reiheID, newBool);
			prueferListe.setChecks(prueferListe.getChecks() + 1);
			listener.reiheChangedEvent(reiheID);
		} else if (oldBool && !newBool) {
			prueferListe.setCheckOnReihe(reiheID, newBool);
			prueferListe.setChecks(prueferListe.getChecks() - 1);
			listener.reiheChangedEvent(reiheID);
		}
	}

	private void checkSpalte(int spalteID) {
		Feld[] spalte = C_RaetselKontrolle.getRaetsel().getSpielfeld().getSpalte(spalteID);
		ArrayList<Integer> spalteNummer = C_RaetselKontrolle.getRaetsel().getNummerListe().getNummerOfSpalte(spalteID);
		PrueferListe prueferListe = C_RaetselKontrolle.getRaetsel().getPrueferListe();
		
		boolean newBool = checkIfKorrekt(spalte, spalteNummer);
		boolean oldBool = prueferListe.getCheckOnSpalte(spalteID);
		if (!oldBool && newBool) {
			prueferListe.setCheckOnSpalte(spalteID, newBool);
			prueferListe.setChecks(prueferListe.getChecks() + 1);
			listener.spalteChangedEvent(spalteID);
		} else if (oldBool && !newBool) {
			prueferListe.setCheckOnSpalte(spalteID, newBool);
			prueferListe.setChecks(prueferListe.getChecks() - 1);
			listener.spalteChangedEvent(spalteID);
		}
	}

	private boolean checkIfKorrekt(Feld[] array, ArrayList<Integer> list) {
		int feld = 0;
		for (int i : list) {
			int count = 0;

			for (; feld < array.length; feld++) {
				if (array[feld] == Feld.BELEGT) {
					count = 1;
					feld++;
					break;
				}
			}
			for (; feld < array.length; feld++) {
				if (array[feld] == Feld.BELEGT) {
					count++;
				} else {
					break;
				}
			}
			if(i!=count) {
				return false;
			}
		}
		for (; feld < array.length; feld++) {
			if (array[feld] == Feld.BELEGT) {
				return false;
			}
		}
		return true;
	}

	public void setListener(PruefListener listener) {
		this.listener = listener;
	}

	public void checkFeld(int reiheID, int spalteID) {
		PrueferListe prueferListe = C_RaetselKontrolle.getRaetsel().getPrueferListe();
		this.checkReihe(reiheID);
		this.checkSpalte(spalteID);
		if (prueferListe.getChecks() == prueferListe.getChecksTotal()) {
			listener.allCorrectEvent();
		}
	}

	public void initCheck() {
		int size = C_RaetselKontrolle.getRaetsel().getSize();
		for (int i = 0; i < size; i++) {
			this.checkFeld(i, i);
		}
	}
}
