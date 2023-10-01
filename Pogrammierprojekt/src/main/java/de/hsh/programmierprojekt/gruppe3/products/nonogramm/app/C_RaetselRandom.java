package de.hsh.programmierprojekt.gruppe3.products.nonogramm.app;

import java.util.ArrayList;
import java.util.Random;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.Feld;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.NummerListe;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.per.Spielfeld;

public class C_RaetselRandom implements C_RaetselGenerator {

	@Override
	public NummerListe generierRaetsel(int size) {
		ArrayList<ArrayList<Integer>> reihe = new ArrayList<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> spalte = new ArrayList<ArrayList<Integer>>();
		Spielfeld feld = new Spielfeld(size);

		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (rand.nextBoolean()) {
					feld.setFeld(i, j, Feld.BELEGT);
				}
			}
		}

		for (int i = 0; i < size; i++) {
			reihe.add(this.arrayZuList(feld.getReihe(i)));
			spalte.add(this.arrayZuList(feld.getSpalte(i)));
		}

		NummerListe output = new NummerListe();
		output.setReihenNummer(reihe);
		output.setSpaltenNummer(spalte);
		return output;
	}

	private ArrayList<Integer> arrayZuList(Feld[] array) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == Feld.BELEGT) {
				count++;
			} else if (count != 0) {
				output.add(count);
				count = 0;
			}
		}
		if (output.isEmpty() && count == 0) {
			output.add(0);
		} else if (count != 0) {
			output.add(count);
		}
		return output;
	}

}
