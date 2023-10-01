package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.tut;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.Const;

public class TUT_PageOne implements TUT_Page {

	@Override
	public void paintPage(Graphics g, Dimension size) {
		int spaceLettersX = 0;
		int spaceLettersY1 = 0;
		int spaceLettersY2 = 0;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("/nonogramm/image/voll_begin.png"));
			BufferedImage image2 = ImageIO.read(getClass().getResource("/nonogramm/image/voll_finish.png"));

			int space = (size.height - image.getHeight() - image2.getHeight() - 50) / 3;

			spaceLettersX = 2 * space + image.getWidth();
			spaceLettersY1 = space;
			spaceLettersY2 = 2 * space + image.getHeight();
			g.drawImage(image, space, space, null);
			g.drawImage(image2, space, image.getHeight() + 2 * space, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setColor(Const.COLOR_PRIMARY);
		g.setFont(new Font("Monospaced", Font.BOLD, Const.FONT_SIZE_PT));

		String text = "In diesem Spiel müsst ihr\ndie Felder so füllen das\nsie mit den Zahlen von\n"
				+ "oben und von links\nübereinstimmen.";

		for (String line : text.split("\n")) {
			spaceLettersY1 += g.getFontMetrics().getHeight();
			g.drawString(line, spaceLettersX, spaceLettersY1);
		}

		String text2 = "So sähe das fertige Spiel\naus.";
		for (String line : text2.split("\n")) {
			spaceLettersY2 += g.getFontMetrics().getHeight();
			g.drawString(line, spaceLettersX, spaceLettersY2);
		}
	}

	@Override
	public TUT_Page nextPage() {
		return new TUT_PageTwo();
	}

	@Override
	public TUT_Page prevPage() {
		return null;
	}
}
