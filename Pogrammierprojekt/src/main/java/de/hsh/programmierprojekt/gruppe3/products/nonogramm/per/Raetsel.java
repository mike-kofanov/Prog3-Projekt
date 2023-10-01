package de.hsh.programmierprojekt.gruppe3.products.nonogramm.per;

public class Raetsel {
	private int size;
	private Spielfeld spielfeld;
	private PrueferListe prueferliste;
	private NummerListe nummerliste;

	public Raetsel(int size, NummerListe nummerliste) {
		this.size = size;
		spielfeld = new Spielfeld(size);
		prueferliste = new PrueferListe(size);
		this.nummerliste=nummerliste;
	}

	public int getSize() {
		return size;
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public NummerListe getNummerListe() {
		return nummerliste;
	}

	public PrueferListe getPrueferListe() {
		return prueferliste;
	}
}
