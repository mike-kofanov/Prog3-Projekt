package de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.app.C_RaetselAuswahl;

public class B_RaetselAuswahl {
	C_RaetselAuswahl schnittstelle = new C_RaetselAuswahl();

	public void raetselEinfach() {
		schnittstelle.raetselEinfach();
	}

	public void raetselMittel() {
		schnittstelle.raetselMittel();
	}

	public void raetselSchwer() {
		schnittstelle.raetselSchwer();
	}
}
