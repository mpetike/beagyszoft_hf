package hu.bme.mit.battle_city.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

import hu.bme.mit.battle_city.gui.Menu.PanelId;

/**
 * Panels' common parent 
 */
public abstract class MenuPanel extends JPanel {

	private static final long serialVersionUID = -8518074592603035700L;
	protected final Menu mWindow;
	protected JButton mBtnBack;

	protected final Logger logger = Logger.getLogger(this.getClass().getName());

	public MenuPanel(Menu menuWindow) { // konstruktor
		mWindow = menuWindow;  // fo menu object �tad a l�trehozott menupanel leszarmaztatott panelnak: pl chossdiff,gamemodeselector
	}

	protected JButton getBackButton(PanelId panel) {
		mBtnBack = new JButton("Back");
		mBtnBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mWindow.showPanel(panel);
			}
		});
		return mBtnBack;
	}
	/*	example to create backbutton 
	 * 	JButton bck = getBackButton();
		bck.setBounds(40,240,100,30);
		add(bck);
		
	 *
	 * Akkor h�v�dik, amikor a panel megjelenik
	 */
	protected void onShow() {

	}

	/**
	 * Akkor h�v�dik, amikor a panel hely�re m�sik ker�l
	 */
	protected void onHide() {

	}

}