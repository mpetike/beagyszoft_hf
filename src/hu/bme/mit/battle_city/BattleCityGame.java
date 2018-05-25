package hu.bme.mit.battle_city;

import hu.bme.mit.battle_city.gui.Menu;

import javax.swing.SwingUtilities;

/**
 * Enter point of the program
 */
public class BattleCityGame {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				showWindow();
			}
		});
	}
	/**
	 * Creates the main menu object and displays it
	 */
	private static void showWindow() {
		Menu mainMenu = new Menu();
		mainMenu.setSize(600, 640); 
		mainMenu.setVisible(true);
	}
}
