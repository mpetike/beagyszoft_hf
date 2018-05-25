package hu.bme.mit.battle_city.gui;

import javax.swing.JFrame;

import hu.bme.mit.battle_city.Network.TCPClient;
import hu.bme.mit.battle_city.Network.TCPServer;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Parent menu class that contains every GUI objects
 * 
 */
public class Menu extends JFrame {

	private static final long serialVersionUID = -6211743596073270671L;
	
	public String MapFolder = "mapfiles/";
	public String currentMap;
	public int difficulty = 0;			 // 0- Easy, 1-Medium, 2-Hard
	public boolean gameMode = false;     // 0-single, 1-Multi
	public boolean clientMode = false;   // 0-Server, 1-Client
	public boolean gameOn = false;		 //Gameplay 0-Off, 1-On
	public int serverPort = 5555;
	public TCPServer server;
	public TCPClient client;

    //Panel object container
	public enum PanelId {
		GAME_MODE_SELECTOR, CHOOSE_DIFFICULTY, MAP_SELECTOR, MULTIPLAYER_PANEL, SERVER_PANEL, CLIENT_PANEL, GAME_FIELD, GET_NAME_PANEL, TOPLIST, ERROR_PANEL
	}

	public Map<PanelId, MenuPanel> mPanels = new HashMap<>();
	private MenuPanel mCurrentPanel = null;

	public Menu() {
		super("Battle City");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initComponents();
	}

	/**
	 * Initialize all the panel objects of the menu system
	 */
	private void initComponents() {
		mPanels.put(PanelId.GAME_MODE_SELECTOR, new GameModeSelector(this));
		mPanels.put(PanelId.CHOOSE_DIFFICULTY, new ChooseDifficulty(this));
		mPanels.put(PanelId.MAP_SELECTOR, new MapSelector(this));
		mPanels.put(PanelId.MULTIPLAYER_PANEL, new MultiplayerPanel(this));
		mPanels.put(PanelId.SERVER_PANEL, new ServerPanel(this));
		mPanels.put(PanelId.CLIENT_PANEL, new ClientPanel(this));
		mPanels.put(PanelId.GAME_FIELD, new GameField(this));

		showPanel(PanelId.GAME_MODE_SELECTOR);
	}

	/**
	 * Shows the given menu panel. 
	 * @param panelId ID of the menu panel
	 */
	public void showPanel(final PanelId panelId) {

		MenuPanel newPanel = mPanels.get(panelId);
		setContentPane(newPanel);
		validate();
		newPanel.requestFocusInWindow();
		mCurrentPanel = newPanel;
	}
}
