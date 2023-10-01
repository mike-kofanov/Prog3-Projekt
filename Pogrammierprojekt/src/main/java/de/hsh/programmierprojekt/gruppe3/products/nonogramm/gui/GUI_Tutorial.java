package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.tut.TUT_Page;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.tut.TUT_PageOne;

public class GUI_Tutorial extends JPanel {
	private static final long serialVersionUID = 6138147344671702740L;
	private TUT_Page page;

	public GUI_Tutorial() {
		this.setSize(Const.TUTORIAL_SIZE);
		this.setLayout(null);
		page = new TUT_PageOne();
		this.buildPanel();
	}

	private void buildNav() {
		this.removeAll();
		this.repaint();
		int spacer = 15;

		if (page.nextPage() != null) {
			@SuppressWarnings("serial")
			GUI_Knopf next = new GUI_Knopf("Nächste Seite") {
				@Override
				public void funktion() {
					page = page.nextPage();
					buildPanel();
				}
			};
			next.setLocation(this.getWidth() - next.getWidth() - spacer, this.getHeight() - next.getHeight() - spacer);
			this.add(next);
		}

		if (page.prevPage() != null) {
			@SuppressWarnings("serial")
			GUI_Knopf back = new GUI_Knopf("Vorherige Seite") {
				@Override
				public void funktion() {
					page = page.prevPage();
					buildPanel();
				}

			};
			back.setLocation(spacer, this.getHeight() - back.getHeight() - spacer);
			this.add(back);
		}
	}

	private void buildPanel() {
		buildNav();
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color background = Const.COLOR_SECONDARY;
		Color foreground = Const.COLOR_PRIMARY;

		g.setColor(foreground);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(background);
		g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
		g.setColor(foreground);

		page.paintPage(g, getMinimumSize());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

}
