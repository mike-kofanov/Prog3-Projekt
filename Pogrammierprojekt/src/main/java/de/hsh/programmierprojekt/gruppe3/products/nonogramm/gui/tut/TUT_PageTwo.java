package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.tut;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui.Const;

public class TUT_PageTwo implements TUT_Page {

	@Override
	public void paintPage(Graphics g, Dimension size) {
		int spaceLettersX1 = 0;
		int spaceLettersX2 = 0;
		int spaceLettersY1 = 0;
		int spaceLettersY2 = 0;
		try {
			BufferedImage image = ImageIO.read(getClass().getResource("/nonogramm/image/single.png"));
			BufferedImage image2 = ImageIO.read(getClass().getResource("/nonogramm/image/row.png"));

			int spaceX = 15;

			spaceLettersX1 = 2 * spaceX + image.getWidth();
			spaceLettersX2 = image.getHeight() + 4 * spaceX;
			spaceLettersY1 = spaceX;
			spaceLettersY2 = 4 * spaceX + image.getHeight();
			g.drawImage(image, spaceX, spaceX + 15, null);
			g.drawImage(image2, spaceX, image.getHeight() + 5 * spaceX, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setColor(Const.COLOR_PRIMARY);
		g.setFont(new Font("Monospaced", Font.BOLD, Const.FONT_SIZE_PT));

		String text = "Mit der linken Maustaste könnt ihr Felder\nbelegen und mit der rechten Maustaste könnt\nihr Felder ankreutzen, um euch zu merken\n"
				+ "wo keine Belegten Felder sind.";

		for (String line : text.split("\n")) {
			spaceLettersY1 += g.getFontMetrics().getHeight();
			g.drawString(line, spaceLettersX1, spaceLettersY1);
		}

		String text2 = "Haltet ihr die jeweilige Taste gedrückt\nkönnt ihr mehrere Felder gleichzeitig\nbelegen oder ankreuzen.";
		for (String line : text2.split("\n")) {
			spaceLettersY2 += g.getFontMetrics().getHeight();
			g.drawString(line, spaceLettersX2, spaceLettersY2);
		}
	}

	@Override
	public TUT_Page nextPage() {
		return null;
	}

	@Override
	public TUT_Page prevPage() {
		return new TUT_PageOne();
	}
}
