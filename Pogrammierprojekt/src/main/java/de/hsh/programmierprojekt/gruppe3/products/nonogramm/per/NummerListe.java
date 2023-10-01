package de.hsh.programmierprojekt.gruppe3.products.nonogramm.per;

import java.util.ArrayList;

public class NummerListe {
	private ArrayList<ArrayList<Integer>> reihenNummer;
	private ArrayList<ArrayList<Integer>> spaltenNummer;

	public void setReihenNummer(ArrayList<ArrayList<Integer>> reihenNummer) {
		this.reihenNummer = reihenNummer;
	}

	public void setSpaltenNummer(ArrayList<ArrayList<Integer>> spaltenNummer) {
		this.spaltenNummer = spaltenNummer;
	}

	public ArrayList<Integer> getNummerOfReihe(int reiheID) {
		return reihenNummer.get(reiheID);
	}

	public ArrayList<Integer> getNummerOfSpalte(int spalteID) {
		return spaltenNummer.get(spalteID);
	}
}
