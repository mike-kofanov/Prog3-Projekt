package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.tut;

import java.awt.Dimension;
import java.awt.Graphics;

public interface TUT_Page {
	public abstract void paintPage(Graphics g, Dimension size);
	public abstract TUT_Page nextPage();
	public abstract TUT_Page prevPage();
}
