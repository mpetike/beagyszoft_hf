package hu.bme.mit.battle_city.gui;

import hu.bme.mit.battle_city.gui.Menu.PanelId;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Game mode selector panel MP or SP
 */
public class GameModeSelector extends MenuPanel {
	private static final long serialVersionUID = -1709524252970560013L;

	private JButton mBtnSingleplayer;
	private JButton mBtnMultiplayer;

	public GameModeSelector(Menu menuWindow) {
		super(menuWindow);
		mBtnMultiplayer = new JButton("Multiplayer");
		mBtnSingleplayer = new JButton("SinglePlayer");

		mBtnSingleplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				menuWindow.gameMode = false;
				menuWindow.showPanel(PanelId.CHOOSE_DIFFICULTY);
			}
		});

		mBtnMultiplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuWindow.showPanel(PanelId.MULTIPLAYER_PANEL);
				menuWindow.gameMode = true;
			}
		});

		setLayout(null);
		mBtnSingleplayer.setBounds(150,100,250,150);
		mBtnMultiplayer.setBounds(150,300,250,150);
		Font bSize18 = new Font("Arial", Font.PLAIN, 18);
		mBtnSingleplayer.setFont(bSize18);
		mBtnMultiplayer.setFont(bSize18);
		add(mBtnSingleplayer);
		add(mBtnMultiplayer);
	}

}