package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

public abstract class GUI_Knopf extends JComponent implements MouseListener {
	private static final long serialVersionUID = -5147534642150299659L;
	private boolean hover = false;
	private Dimension size;

	String text;

	public GUI_Knopf(String text) {
		this();
		this.text = text;
		size = new Dimension((2 + text.length()) * Const.FONT_SIZE_WIDTH, 50);
		setSize(size);
	}

	public GUI_Knopf() {
		addMouseListener(this);
		setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color background;
		Color foreground;
		if (hover) {
			background = Const.COLOR_PRIMARY;
			foreground = Const.COLOR_SECONDARY;
		} else {
			background = Const.COLOR_SECONDARY;
			foreground = Const.COLOR_PRIMARY;
		}
		g.setColor(foreground);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(background);
		g.fillRect(3, 3, this.getWidth() - 6, this.getHeight() - 6);
		g.setColor(foreground);
		g.setFont(new Font("Monospaced", Font.BOLD, Const.FONT_SIZE_PT));
		g.drawString(text, (this.getWidth() - text.length() * Const.FONT_SIZE_WIDTH) / 2,
				(this.getHeight() + Const.FONT_SIZE_HEIGHT) / 2);
	}

	public abstract void funktion() throws Exception;

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			funktion();
			GUI_Sound sound = new GUI_Sound();
			sound.playClick();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		hover = true;
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		hover = false;
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		repaint();
	}
}