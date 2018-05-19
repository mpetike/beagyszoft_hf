package hu.bme.mit.battle_city.GameLogic;

import java.util.ArrayList;

public class GameWorld implements Runnable{
	public boolean[][] MapGridArray;
	public boolean SingleOrMulti;
	private int TimeElapsed;
	private int Difficulty;
	
	//Game objects
	ArrayList<PlayerTank> AlivePlayerTanks;
	ArrayList<AiTank> AliveAiTanks;
	//Frame timing
	public int FrameTime = 33;
	//Thread variables
	private Thread GameLogicThread;
	
	/**
	 * 
	 * @param map_path	path to mapfile
	 * @param sp_mode	true-singleplayer false-multiplayer
	 * @param difficulty 0 - easy 1 - medium 2 - hard
	 * @param InputQueue - queue holding input keys
	 * @param RemoteInput - Remote player input
	 */
	public GameWorld(String map_path, boolean sp_mode, int difficulty,boolean InputQueue,boolean RemoteInput) {
		SingleOrMulti = sp_mode;
		MapGridArray = GameLogicUtility.LoadMapFromFile(map_path);
		Difficulty = difficulty;
	}
	
	private void UpdateFrame() {
		
	}
	
	/**
	 * 
	 * @param Type 0-AiTank 1-Player 2-RemotePlayer
	 */
	private void CreateNewTank(int Type) {
		if(Type == 1) {
			int[] pos = GameLogicUtility.RandomPositionGen(MapGridArray);
			AlivePlayerTanks.add(new PlayerTank(pos[0], pos[1], LocRemote))
		}
	}
	
	
	//Game logic control
	public void StartGame() {
		//Do some shit, initialize game
		
		//Start thread
		if(GameLogicThread == null) {
			System.out.println("Starting thread");
			GameLogicThread = new Thread(this,"GAMEWORLD");
			GameLogicThread.start();
		}
		//Terminate
		
	}
	
	//Run game logic
	public void run() {
		//Game logic be here
		try {
			//Run main routine
			//Wait
			Thread.sleep(FrameTime);
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}

	}
}
