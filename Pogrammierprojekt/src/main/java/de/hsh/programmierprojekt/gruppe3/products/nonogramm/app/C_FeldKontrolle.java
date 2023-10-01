package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.PruefListener;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.State;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.Feld;

public class C_FeldKontrolle {
	private C_Pruefer pruefer = new C_Pruefer();

	private void setFeld(int reiheID, int spalteID, Feld neuFeld) {
		Feld feld = C_RaetselKontrolle.getRaetsel().getSpielfeld().getFeld(reiheID, spalteID);
		if (feld == Feld.LEER) {
			C_RaetselKontrolle.getRaetsel().getSpielfeld().setFeld(reiheID, spalteID, neuFeld);
		} else {
			C_RaetselKontrolle.getRaetsel().getSpielfeld().setFeld(reiheID, spalteID, Feld.LEER);
		}
		pruefer.checkFeld(reiheID, spalteID);
	}

	public void setBelegt(int reiheID, int spalteID) {
		this.setFeld(reiheID, spalteID, Feld.BELEGT);
	}

	public void setUnbelegt(int reiheID, int spalteID) {
		this.setFeld(reiheID, spalteID, Feld.UNBELEGT);
	}

	public State getStateOfFeld(int reiheID, int spalteID) {
		Feld feld = C_RaetselKontrolle.getRaetsel().getSpielfeld().getFeld(reiheID, spalteID);
		if (feld == Feld.BELEGT) {
			return State.BELEGT;
		}
		if (feld == Feld.UNBELEGT) {
			return State.UNBELEGT;
		}
		return State.LEER;
	}

	public void setListenerOfPruefer(PruefListener listener) {
		pruefer.setListener(listener);
	}

	public void initCheck() {
		pruefer.initCheck();
	}
}
