package hu.bme.mit.battle_city.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	/**
	 * 
	 * @param menuWindow
	 */
	public MenuPanel(Menu menuWindow) { 
		mWindow = menuWindow; 
		
	}
	/**
	 * Back button creator on a panel
	 * @param panel - Panel object to create on
	 * @return Created button object
	 */
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


}