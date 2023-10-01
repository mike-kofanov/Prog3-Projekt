package de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.app.C_FeldKontrolle;

public class B_FeldKontrolle {
	C_FeldKontrolle schnittstelle = new C_FeldKontrolle();

	public void setBelegt(int reiheID, int spalteID) {
		schnittstelle.setBelegt(reiheID, spalteID);
	}

	public void setUnbelegt(int reiheID, int spalteID) {
		schnittstelle.setUnbelegt(reiheID, spalteID);
	}

	public State getStateOfFeld(int reiheID, int spalteID) {
		return schnittstelle.getStateOfFeld(reiheID, spalteID);
	}

	public void setListenerOfPruefer(PruefListener listener) {
		schnittstelle.setListenerOfPruefer(listener);
	}

	public void initCheck() {
		schnittstelle.initCheck();
	}
}
