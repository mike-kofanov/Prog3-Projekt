package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.B_FeldKontrolle;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.B_Raetsel;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.State;

public class GUI_Spiel extends JPanel {
	private static final long serialVersionUID = -7914867384501354310L;
	private Dimension size;
	private Dimension bufferSize;
	private Dimension spielfeldSize;
	private B_FeldKontrolle feldkontrolle = new B_FeldKontrolle();
	private B_Raetsel raetsel = new B_Raetsel();

	private GUI_NummerSpalte[] nummerSpalte;
	private GUI_NummerReihe[] nummerReihe;
	private Set<GUI_Feld> felder = new HashSet<GUI_Feld>();

	public GUI_Spiel(GUI_PruefKontrolle pruefer) {
		this.setBackground(Const.COLOR_SECONDARY);
		feldkontrolle.setListenerOfPruefer(pruefer);
		buildRaetsel();
	}

	public void setKorrektReihe(int reiheID) {
		nummerReihe[reiheID].setKorrekt(raetsel.getCheckOnReihe(reiheID));
	}

	public void setKorrektSpalte(int spalteID) {
		nummerSpalte[spalteID].setKorrekt(raetsel.getCheckOnSpalte(spalteID));
	}

	private void buildRaetsel() {
		int sizeReihe = raetsel.getMaxSizeReihe();
		int sizeSpalte = raetsel.getMaxSizeSpalte();

		int size = raetsel.getSize();
		this.spielfeldSize = new Dimension(
				size * Const.FELD_SIZE_WIDTH + (size + 1) * Const.FELD_GAP + Const.BIG_GAP * (size / 6),
				size * Const.FELD_SIZE_HEIGHT + (size + 1) * Const.FELD_GAP + Const.BIG_GAP * (size / 6));
		this.bufferSize = new Dimension(Const.FELD_SIZE_WIDTH * sizeReihe, Const.FELD_SIZE_HEIGHT * sizeSpalte);
		this.size = new Dimension(bufferSize.width + spielfeldSize.width, bufferSize.height + spielfeldSize.height);

		this.setSize(this.size);

		this.setLayout(null);
		buildSpalte(sizeSpalte);
		buildReihe(sizeReihe);
		buildSpielfeld();
	}

	private void buildReihe(int sizeReihe) {
		int size = raetsel.getSize();
		JPanel panel = new JPanel();
		panel.setLocation(0, bufferSize.height);
		panel.setSize(bufferSize.width, spielfeldSize.height);
		panel.setBackground(Const.COLOR_PRIMARY);
		nummerReihe = new GUI_NummerReihe[size];
		int space = 0;
		for (int i = 0; i < size; i++) {
			if (i % 5 == 0 & i > 0) {
				space += Const.BIG_GAP;
			}
			nummerReihe[i] = new GUI_NummerReihe(sizeReihe, raetsel.getNummerOfReihe(i));
			nummerReihe[i].setLocation(0, Const.FELD_GAP + i * (Const.FELD_GAP + nummerReihe[i].getHeight()) + space);

			panel.add(nummerReihe[i]);
		}
		this.add(panel);
	}

	private void buildSpalte(int sizeSpalte) {
		int size = raetsel.getSize();
		JPanel panel = new JPanel();
		panel.setLocation(bufferSize.width, 0);
		panel.setSize(spielfeldSize.width, bufferSize.height);
		panel.setBackground(Const.COLOR_PRIMARY);
		nummerSpalte = new GUI_NummerSpalte[size];
		int space = 0;
		for (int i = 0; i < size; i++) {
			if (i % 5 == 0 & i > 0) {
				space += Const.BIG_GAP;
			}
			nummerSpalte[i] = new GUI_NummerSpalte(sizeSpalte, raetsel.getNummerOfSpalte(i));
			nummerSpalte[i].setLocation(Const.FELD_GAP + i * (Const.FELD_GAP + nummerSpalte[i].getWidth()) + space, 0);

			panel.add(nummerSpalte[i]);
		}
		this.add(panel);
	}

	private void buildSpielfeld() {
		int size = raetsel.getSize();
		JPanel panel = new JPanel();
		panel.setLocation(bufferSize.width, bufferSize.height);
		panel.setSize(spielfeldSize);
		panel.setBackground(Const.COLOR_PRIMARY);
		int spaceY = 0;
		for (int i = 0; i < size; i++) {
			int spaceX = 0;
			if (i % 5 == 0 & i > 0) {
				spaceY += Const.BIG_GAP;
			}
			for (int j = 0; j < size; j++) {
				if (j % 5 == 0 & j > 0) {
					spaceX += Const.BIG_GAP;
				}
				GUI_Feld feld = new GUI_Feld(new Point(i, j), feldkontrolle);
				feld.setLocation(Const.FELD_GAP + j * (Const.FELD_GAP + feld.getWidth()) + spaceX,
						Const.FELD_GAP + i * (Const.FELD_GAP + feld.getHeight()) + spaceY);
				panel.add(feld);
				felder.add(feld);
			}
		}
		this.add(panel);
	}

	public void blockInput() {
		for (GUI_Feld f : felder) {
			f.removeMouseListener(f);
			f.setHover(false);
			f.resetDrag();
			if (f.getState() == State.UNBELEGT) {
				f.setState(State.LEER);
			}
			f.repaint();
		}

	}

	public void initCheck() {
		feldkontrolle.initCheck();
	}
}
