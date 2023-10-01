package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JComponent;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.*;

public class GUI_Feld extends JComponent implements MouseListener {
	private static final long serialVersionUID = 6678768170803618600L;
	private boolean hover = false;
	private Dimension size = new Dimension(Const.FELD_SIZE_WIDTH, Const.FELD_SIZE_HEIGHT);
	private Point pos = new Point();
	private B_FeldKontrolle feldkontrolle;

	private State state = State.LEER;

	public GUI_Feld(Point pos, B_FeldKontrolle feldkontrolle) {
		addMouseListener(this);

		setSize(size.width, size.height);
		setFocusable(true);

		this.pos = pos;
		this.feldkontrolle = feldkontrolle;
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Color background = Const.COLOR_SECONDARY;
		Color foreground = Const.COLOR_PRIMARY;
		if (hover) {
			g2d.setColor(Const.COLOR_PRIMARY);
		} else {
			g2d.setColor(Const.COLOR_SECONDARY);
		}
		g2d.fillRect(0, 0, size.width, size.height);
		g2d.setColor(background);
		g2d.fillRoundRect(3, 3, size.width - 6, size.height - 6, 4, 4);
		switch (state) {
		case BELEGT:
			g2d.setColor(foreground);
			g2d.fillRoundRect(3, 3, size.width - 6, size.height - 6, 4, 4);
			break;
		case UNBELEGT:
			g2d.setColor(foreground);
			g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2d.drawLine(4, 4, size.width - 5, size.height - 5);
			g2d.drawLine(size.width - 5, 4, 4, size.height - 5);
			break;
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

	private static int last_pressed = 0;
	private static State stateOnPressed;
	private static boolean button_hold = false;

	private void buttonPressed(int button) {
		switch (button) {
		case MouseEvent.BUTTON1:
			feldkontrolle.setBelegt(pos.x, pos.y);
			break;
		case MouseEvent.BUTTON3:
			feldkontrolle.setUnbelegt(pos.x, pos.y);
			break;
		}
		state = feldkontrolle.getStateOfFeld(pos.x, pos.y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		stateOnPressed = this.state;
		last_pressed = e.getButton();
		button_hold = true;
		buttonPressed(last_pressed);
		GUI_Sound sound = new GUI_Sound();
		sound.playClick();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		button_hold = false;
		last_pressed = MouseEvent.NOBUTTON;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		hover = true;
		if (button_hold & (this.state == stateOnPressed || this.state == State.UNBELEGT & stateOnPressed == State.BELEGT
				|| this.state == State.BELEGT & stateOnPressed == State.UNBELEGT)) {
			buttonPressed(last_pressed);
			GUI_Sound sound = new GUI_Sound();
			sound.playClick();
		}
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		hover = false;
		this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		repaint();
	}

	public void setHover(boolean hover) {
		this.hover = hover;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState() {
		return this.state;
	}

	public void resetDrag() {
		last_pressed = 0;
		button_hold = false;
	}
}
