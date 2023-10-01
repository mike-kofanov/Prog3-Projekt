package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GUI_NummerReihe extends JComponent {
	private static final long serialVersionUID = 6428685633179460196L;
	private Dimension size;
	private int maxSize;
	private ArrayList<Integer> list;
	private boolean korrekt = false;

	public GUI_NummerReihe(int maxSize, ArrayList<Integer> list) {
		this.maxSize = maxSize;
		this.list = list;
		size = new Dimension(Const.FELD_SIZE_WIDTH * maxSize, Const.FELD_SIZE_HEIGHT);
		this.setSize(size);
	}

	public void setKorrekt(boolean korrekt) {
		this.korrekt = korrekt;
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color background;
		Color letters;
		if (korrekt) {
			background = Const.COLOR_PRIMARY;
			letters = Const.COLOR_SECONDARY;
		} else {
			background = Const.COLOR_SECONDARY;
			letters = Const.COLOR_PRIMARY;
		}
		g.setColor(background);
		g.fillRect(0, 0, size.width, size.height);
		g.setFont(new Font("Monospaced", Font.BOLD, Const.FONT_SIZE_PT));
		g.setColor(letters);
		for (int i = maxSize - list.size(), j = 0; i < maxSize; i++, j++) {
			int width = 1;
			if (list.get(j) % 10 != list.get(j)) {
				width = 2;
			}
			int x = (Const.FELD_SIZE_WIDTH - width * Const.FONT_SIZE_WIDTH) / 2 + (i * Const.FELD_SIZE_WIDTH);
			int y = (Const.FELD_SIZE_HEIGHT + Const.FONT_SIZE_HEIGHT) / 2;
			g.drawString("" + list.get(j), x, y);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
