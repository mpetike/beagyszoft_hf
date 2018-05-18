package hu.bme.mit.battle_city;

import hu.bme.mit.battle_city.gui.Menu;

import javax.swing.SwingUtilities;

/**
 * A program bel�p�si pontja
 */
public class BattleCityGame {

	public static void main(String[] args) {
		// A grafikus fel�letet a Swing saj�t esem�nykezelo sz�l�n kell
		// l�trehozni
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
