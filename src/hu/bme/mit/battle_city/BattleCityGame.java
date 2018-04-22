package hu.bme.mit.battle_city;

import hu.bme.mit.battle_city.gui.Menu;

import java.awt.Color;

import javax.swing.SwingUtilities;

/**
 * A program belépési pontja
 */
public class BattleCityGame {

	public static void main(String[] args) {
		// A grafikus felületet a Swing saját eseménykezelő szálán kell
		// létrehozni
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				showWindow();
			}
		});
	}

	private static void showWindow() {
		Menu gameWindow = new Menu();
		gameWindow.setSize(600, 600); 
		gameWindow.setVisible(true);
	}
}
