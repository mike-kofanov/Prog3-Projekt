package de.hsh.programmierprojekt.gruppe3.products.nonogramm.gui;

import java.io.IOException;

import javax.swing.JFrame;
import de.hsh.programmierprojekt.gruppe3.core.MainMenuController;
import de.hsh.programmierprojekt.gruppe3.products.nonogramm.bou.B_RaetselAuswahl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUI_Kontrolle {
	GUI_Spiel spielfeld;
	JFrame frame;
	int diff = 0;

	@SuppressWarnings("serial")
	private GUI_Knopf uebersicht = new GUI_Knopf("Übersicht") {
		@Override
		public void funktion() throws IOException {
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.dispose();
			
			Platform.runLater(() -> {
				try {
					Stage stage = new Stage();
			        FXMLLoader fxmlLoader = new FXMLLoader();
			        Pane root = fxmlLoader.load(getClass().getResource("/de/hsh/programmierprojekt/gruppe3/core/mainMenu.fxml").openStream());
			        MainMenuController controller = fxmlLoader.getController();
			        controller.setBackgroundImages();
			        Scene scene = new Scene(root);
			        stage.setScene(scene);
			        stage.setTitle("Spielesammlung Dark IT");
			        stage.setResizable(false);
			        stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	};

	public GUI_Kontrolle() {
		this("Default");
	}

	public GUI_Kontrolle(String text) {
		frame = new JFrame(text);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Const.FRAME_SIZE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Const.COLOR_SECONDARY);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	public void changeToFrameTwo() {
		frame.getContentPane().removeAll();
		frame.repaint();

		spielfeld = new GUI_Spiel(new GUI_PruefKontrolle(this));
		spielfeld.initCheck();
		spielfeld.setLocation((frame.getContentPane().getWidth() - spielfeld.getWidth()) / 2,
				(frame.getContentPane().getHeight() - spielfeld.getHeight()) / 2);
		frame.add(spielfeld);

		@SuppressWarnings("serial")
		GUI_Knopf auswahl = new GUI_Knopf("Auswahl") {
			@Override
			public void funktion() {
				changeToFrameOne();
			}
		};

		uebersicht.setLocation(5, 5);
		auswahl.setLocation(5, 60);

		frame.add(uebersicht);
		frame.add(auswahl);
		frame.repaint();
	}

	@SuppressWarnings("serial")
	public void changeToFrameOne() {
		frame.getContentPane().removeAll();
		frame.repaint();
		B_RaetselAuswahl auswahl = new B_RaetselAuswahl();

		GUI_Knopf einfach = new GUI_Knopf("Rätsel Einfach") {
			@Override
			public void funktion() {
				auswahl.raetselEinfach();
				diff = 1;
				changeToFrameTwo();
			}
		};
		GUI_Knopf mittel = new GUI_Knopf("Rätsel Mittel") {
			@Override
			public void funktion() {
				auswahl.raetselMittel();
				diff = 2;
				changeToFrameTwo();
			}
		};
		GUI_Knopf schwer = new GUI_Knopf("Rätsel Schwer") {
			@Override
			public void funktion() {
				auswahl.raetselSchwer();
				diff = 3;
				changeToFrameTwo();
			}
		};

		uebersicht.setLocation(5, 5);
		einfach.setLocation(5, 60);
		mittel.setLocation(5, 115);
		schwer.setLocation(5, 170);

		GUI_Tutorial tutorial = new GUI_Tutorial();
		tutorial.setLocation(300, 60);
		frame.add(tutorial);

		frame.add(uebersicht);
		frame.add(einfach);
		frame.add(mittel);
		frame.add(schwer);
		frame.repaint();
	}

	public void raetselWon() {
		spielfeld.blockInput();
		GUI_Sound sound = new GUI_Sound();
		sound.playWin();

		GUI_Text text = new GUI_Text("Gewonnen!");
		text.setLocation((frame.getContentPane().getWidth() - text.getWidth()) / 2,
				(frame.getContentPane().getHeight() - spielfeld.getHeight()) / 2 - 50);

		B_RaetselAuswahl auswahl = new B_RaetselAuswahl();

		@SuppressWarnings("serial")
		GUI_Knopf zurUebersicht = new GUI_Knopf("Nochmal?") {
			@Override
			public void funktion() {
				switch (diff) {
				case 1:
					auswahl.raetselEinfach();
					break;
				case 2:
					auswahl.raetselMittel();
					break;
				case 3:
					auswahl.raetselSchwer();
					break;
				}
				changeToFrameTwo();
			}

		};
		zurUebersicht.setLocation((frame.getContentPane().getWidth() - zurUebersicht.getWidth()) / 2,
				(frame.getContentPane().getHeight() + spielfeld.getHeight()) / 2 + 10);
		frame.add(text);
		frame.add(zurUebersicht);
		frame.repaint();
	}

	public void setKorrektReihe(int reiheID) {
		spielfeld.setKorrektReihe(reiheID);
	}

	public void setKorrektSpalte(int spalteID) {
		spielfeld.setKorrektSpalte(spalteID);
	}
}
