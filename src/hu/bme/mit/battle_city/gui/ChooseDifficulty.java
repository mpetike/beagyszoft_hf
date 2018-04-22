package hu.bme.mit.battle_city.gui;

import hu.bme.mit.battle_city.gui.Menu.PanelId;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * MP panel choose between "Join a game" or "Start server"
 */
public class ChooseDifficulty extends MenuPanel {
	private static final long serialVersionUID = 8978172251199097146L;

	private JButton mBtnEasy;
	private JButton mBtnMedium;
	private JButton mBtnHard;
	private JButton mBtnBack;
	public ChooseDifficulty(Menu menuWindow) {
		super(menuWindow);


		mBtnEasy = new JButton("Easy");
		mBtnEasy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mWindow.showPanel(PanelId.SERVER_PANEL);
			}
		});

		mBtnMedium = new JButton("Medium");
		mBtnMedium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mWindow.showPanel(PanelId.CLIENT_PANEL);
			}
		});
		mBtnHard = new JButton("Hard");
		mBtnHard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				mWindow.showPanel(PanelId.CLIENT_PANEL);
			}
		});
		
		setLayout(null);
		mBtnBack = getBackButton(PanelId.GAME_MODE_SELECTOR);
		mBtnEasy.setBounds(150,100,250,100);
		mBtnMedium.setBounds(150,200,250,100);
		mBtnHard.setBounds(150,300,250,100);
		mBtnBack.setBounds(150,450,250,50);
		Font bSize18 = new Font("Arial", Font.PLAIN, 18);
		mBtnEasy.setFont(bSize18);
		mBtnMedium.setFont(bSize18);	
		mBtnHard.setFont(bSize18);	
		mBtnBack.setFont(bSize18);	
		add(mBtnEasy);
		add(mBtnMedium);
		add(mBtnHard);
		add(mBtnBack);
	}
}