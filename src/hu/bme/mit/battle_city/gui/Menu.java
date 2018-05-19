package hu.bme.mit.battle_city.gui;

import javax.swing.JFrame;


import java.util.HashMap;
import java.util.Map;

/**
 * Program Menu
 */
public class Menu extends JFrame {

	private static final long serialVersionUID = -6211743596073270671L;
	public String MapFolder = "mapfiles/";
	public String currentMap; 
	public int difficulty;
	public int gameMode; // 0-single, 1-MultiServer, 2-MultiClient
	public int serverPort = 5555;
	
	public enum PanelId {
		GAME_MODE_SELECTOR,CHOOSE_DIFFICULTY, MAP_SELECTOR, MULTIPLAYER_PANEL, SERVER_PANEL, CLIENT_PANEL, GAME_FIELD, GET_NAME_PANEL, TOPLIST, ERROR_PANEL
	}
	private Map<PanelId, MenuPanel> mPanels = new HashMap<>();
	private MenuPanel mCurrentPanel = null;
	
	public Menu() 
			{
			super("Battle City");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			initComponents();
			}
	
	

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
	 * A {@link PanelId}-val azonosított felhasználói felület elem megjelenítése
	 * 
	 * @param panelId
	 */
	public void showPanel(final PanelId panelId) {
		if(mCurrentPanel != null){
			mCurrentPanel.onHide();
		}
		
		MenuPanel newPanel = mPanels.get(panelId);
		setContentPane(newPanel);
		validate();
		newPanel.requestFocusInWindow();
		newPanel.onShow();
		mCurrentPanel = newPanel;
	}
	}
