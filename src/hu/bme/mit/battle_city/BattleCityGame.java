package hu.bme.mit.battle_city;

import hu.bme.mit.battle_city.gui.Menu;

import javax.swing.SwingUtilities;

/**
 * A program belépési pontja
 */
public class BattleCityGame {

	public static void main(String[] args) {
		// A grafikus felületet a Swing saját eseménykezelo szálán kell
		// létrehozni
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				showWindow();
			}
		});
	}

	private static void showWindow() {
		Menu mainMenu = new Menu();
		mainMenu.setSize(600, 640); 
		mainMenu.setVisible(true);
	}
}
