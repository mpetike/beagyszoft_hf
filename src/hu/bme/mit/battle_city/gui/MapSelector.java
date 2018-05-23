package hu.bme.mit.battle_city.gui;

import hu.bme.mit.battle_city.gui.Menu.PanelId;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

/**
 * Map selection
 */
public class MapSelector extends MenuPanel {
	private static final long serialVersionUID = 8978172251199097146L;

	GameField gameField;
	JList<String> list;
	DefaultListModel<String> model;
	JButton mBtnBack;
	public MapSelector(Menu menuWindow) {
		super(menuWindow);
		setLayout(null);
		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		JScrollPane pane = new JScrollPane(list);
		JButton selectButton = new JButton("Select");
		JButton mBtnBack = new JButton("Back");
		ArrayList<String> maps = ReadMapDir();

		for (int i = 0; i < maps.size(); i++) {
			model.addElement(maps.get(i));
		}

		selectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				if (i >= 0) {
					menuWindow.currentMap = maps.get(i);
					
					if(menuWindow.gameMode)
					{
						mWindow.showPanel(PanelId.SERVER_PANEL);
					}
					else {
					mWindow.showPanel(PanelId.GAME_FIELD);
					gameField=(GameField) menuWindow.mPanels.get(PanelId.GAME_FIELD);
					gameField.startGame();
					}
				}
			}

		});

		mBtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(menuWindow.gameMode)
					{
						mWindow.showPanel(PanelId.MULTIPLAYER_PANEL);
					}
					else {
						mWindow.showPanel(PanelId.CHOOSE_DIFFICULTY);
					}
				}
			}

		);
		pane.setBounds(150, 100, 250, 200);
		selectButton.setBounds(150, 300, 250, 100);
		mBtnBack.setBounds(150, 450, 250, 50);

		Font bSize18 = new Font("Arial", Font.PLAIN, 18);
		selectButton.setFont(bSize18);
		mBtnBack.setFont(bSize18);

		add(pane);
		add(selectButton);
		add(mBtnBack);

	}

	public ArrayList<String> ReadMapDir() {
		String path = "mapfiles";

		ArrayList<String> files = new ArrayList<String>();
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i].getName());
				System.out.println(files);
			}
		}

		return files;
	}

}
