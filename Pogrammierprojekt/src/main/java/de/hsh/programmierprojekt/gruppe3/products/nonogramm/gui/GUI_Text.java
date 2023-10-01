package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JComponent;

public class GUI_Text extends JComponent {
	private static final long serialVersionUID = -5147534642150299659L;
	private Dimension size;

	String text;

	public GUI_Text(String text) {
		this.text = text;
		size = new Dimension((2 + text.length()) * Const.FONT_SIZE_WIDTH, 50);
		setSize(size);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Const.COLOR_PRIMARY);
		g.setFont(new Font("Monospaced", Font.BOLD, Const.FONT_SIZE_PT));
		g.drawString(text, (this.getWidth() - text.length() * Const.FONT_SIZE_WIDTH) / 2,
				(this.getHeight() + Const.FONT_SIZE_HEIGHT) / 2);
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