package hu.bme.mit.battle_city.gui;

import hu.bme.mit.battle_city.gui.Menu.PanelId;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * MP panel class where you have to choose between server or client mode.
 * Buttons: Join a game, Start server, Back 
 * Next panel: Map selector or Client panel
 */
public class MultiplayerPanel extends MenuPanel {
	private static final long serialVersionUID = 8978172251199097146L;

	private JButton mBtnServer;
	private JButton mBtnClient;
	private JButton mBtnBack;
	MapSelector mapSelector;

	/**
	 * 
	 * @param menuWindow
	 */
	public MultiplayerPanel(Menu menuWindow) {
		super(menuWindow);

		mBtnServer = new JButton("Start server");
		mBtnServer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				menuWindow.clientMode = false;
				mWindow.showPanel(PanelId.MAP_SELECTOR);
			}
		});

		mBtnClient = new JButton("Join game");
		mBtnClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				menuWindow.clientMode = true;
				mWindow.showPanel(PanelId.CLIENT_PANEL);
			}
		});
		setLayout(null);
		mBtnBack = getBackButton(PanelId.GAME_MODE_SELECTOR);
		mBtnServer.setBounds(150, 150, 250, 100);
		mBtnClient.setBounds(150, 250, 250, 100);
		mBtnBack.setBounds(150, 400, 250, 50);
		Font bSize18 = new Font("Arial", Font.PLAIN, 18);
		mBtnServer.setFont(bSize18);
		mBtnClient.setFont(bSize18);
		mBtnBack.setFont(bSize18);
		add(mBtnServer);
		add(mBtnClient);
		add(mBtnBack);
	}
}
