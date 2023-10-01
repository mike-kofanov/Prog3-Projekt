package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.Raetsel;

public class C_RaetselKontrolle {
	private static Raetsel raetsel;

	public static void erstelleRaetsel(int size, C_RaetselGenerator generator) {
		raetsel = new Raetsel(size, generator.generierRaetsel(size));
	}

	public static Raetsel getRaetsel() {
		return raetsel;
	}
}
