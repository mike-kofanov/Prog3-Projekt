package de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou;

public interface PruefListener {
	public abstract void reiheChangedEvent(int reiheID);

	public abstract void spalteChangedEvent(int spalteID);

	public abstract void allCorrectEvent();
}
