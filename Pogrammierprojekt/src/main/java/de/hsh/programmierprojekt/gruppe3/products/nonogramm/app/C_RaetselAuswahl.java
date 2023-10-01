package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

public class C_RaetselAuswahl {
	public void raetselEinfach() {
		C_RaetselKontrolle.erstelleRaetsel(7, new C_RaetselRandom());
	}

	public void raetselMittel() {
		C_RaetselKontrolle.erstelleRaetsel(10, new C_RaetselRandom());
	}

	public void raetselSchwer() {
		C_RaetselKontrolle.erstelleRaetsel(15, new C_RaetselRandom());
	}
}
