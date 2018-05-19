package hu.bme.mit.battle_city.GameLogic;

import java.util.ArrayList;
import java.util.Queue;

import javax.swing.SwingUtilities;

import hu.bme.mit.battle_city.gui.GameField;

public class GameWorld implements Runnable{
	public boolean[][] MapGridArray;
	public boolean SingleOrMulti;
	private int TimeElapsed;
	private int Difficulty;
	
	//Game objects
	public ArrayList<PlayerTank> AlivePlayerTanks;
	public ArrayList<AiTank> AliveAiTanks;
	public ArrayList<CannonShell> AliveShells;
	public ArrayList<Explosion> ActiveExplosions;
	//Frame timing
	public int FrameTime = 300;
	//Thread variables
	private Thread GameLogicThread;
	
	public GameField gamfield;
	
	/**
	 * 
	 * @param map_path	path to mapfile
	 * @param sp_mode	true-singleplayer false-multiplayer
	 * @param difficulty 0 - easy 1 - medium 2 - hard
	 * @param InputQueue - queue holding input keys
	 * @param RemoteInput - Remote player input
	 */
	public GameWorld(String map_path, boolean sp_mode, int difficulty, Queue<Integer> InputQueue,Queue<Integer> RemoteInput, GameField gameFieldObj) {
		SingleOrMulti = sp_mode;
		MapGridArray = GameLogicUtility.LoadMapFromFile(map_path);
		Difficulty = difficulty;
		//Prep lists
		AlivePlayerTanks = new ArrayList<PlayerTank>();
		AliveAiTanks = new ArrayList<AiTank>();
		AliveShells = new ArrayList<CannonShell>();
		ActiveExplosions = new ArrayList<Explosion>();
		gamfield = gameFieldObj;
	}
	
	/**
	 * Function running routines for each frame calculation
	 */
	private void UpdateFrame() {
		//NextMove for player tanks
		for(PlayerTank player:AlivePlayerTanks) {
			player.NextMove(this);
		}
		//NextMove for Ai tanks
		
		//NextMove for shells
		for(CannonShell shells:AliveShells) {
			shells.NextMove(this);
		}		
		//Clear dead objects
		DestroyDeadThings();
		//Game over conditions
		
	}
	
	/**
	 * 
	 * @param Type 0-AiTank 1-Player 2-RemotePlayer
	 */
	private void CreateNewTank(int Type) {
		if(Type == 1) {
			int[] pos = GameLogicUtility.RandomPositionGen(MapGridArray);
			AlivePlayerTanks.add(new PlayerTank(pos[0], pos[1], false));
		}
	}
		
	/**
	 * Removes dead objects from active objcts list
	 */
	private void DestroyDeadThings() {
		//AI tanks
		//Shells
		//Explosions
		//Player tanks
	}
	
	//Game logic control
	public void StartGame() {
		//Do some shit, initialize game
		CreateNewTank(1);
		//Start thread
		if(GameLogicThread == null) {
			System.out.println("Starting thread");
			GameLogicThread = new Thread(this,"GAMEWORLD");
			GameLogicThread.start();
		}
		//Terminate
		
	}
	
	private void callFrameUpdate() {
		SwingUtilities.invokeLater(new Runnable() 
	    {
	      public void run()
	      {
	    	  gamfield.updateFrame();
	      }		
	    });
	}
	
	//Run game logic
	public void run() {
		//Game logic be here
		try {
			//Run main routine
			while(true) {
			UpdateFrame();
			callFrameUpdate();
			//Wait
			Thread.sleep(FrameTime);
			}
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

	}
}