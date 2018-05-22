package hu.bme.mit.battle_city.GameLogic;

import java.util.ArrayList;
import java.util.Queue;

import javax.swing.SwingUtilities;

import hu.bme.mit.battle_city.gui.GameField;

/**
 * Main class holding the game logic
 * @author Peti
 *
 */
public class GameWorld implements Runnable{
	public boolean[][] MapGridArray;
	public boolean SingleOrMulti;
	@SuppressWarnings("unused")
	private double TimeElapsed;
	private int Difficulty;
	private boolean GameOver;
	
	//Game objects
	public ArrayList<PlayerTank> AlivePlayerTanks;
	public ArrayList<AiTank> AliveAiTanks;
	public ArrayList<CannonShell> AliveShells;
	public ArrayList<Explosion> ActiveExplosions;
	//Frame timing
	public int FrameTime = 33;	//ms
	//Thread variables
	private Thread GameLogicThread;
	//Input queues
	Queue<Integer> LocalPlayerQueue;
	Queue<Integer> RemotePlayerQueue;
	//Object holding renderer
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
		LocalPlayerQueue = InputQueue;	//Local player inputs
		RemotePlayerQueue = RemoteInput;	//Remote player inputs
		GameOver = false;
	}
	
	/**
	 * Function running routines for each frame calculation
	 */
	private void UpdateFrame() {
		//Evaluate game condition
		if(SingleOrMulti == false) {
			if(AlivePlayerTanks.isEmpty())
				GameOver = true;
		}		
		//if in multiplayer, recreate destroyed tanks
		if(SingleOrMulti == true) {
			boolean localdead = true,remotedead = true;
			for(PlayerTank player:AlivePlayerTanks) {
				if(player.LocalOrRemote == true)
					localdead = false;
				else 
					remotedead = false;				
			}
			if(localdead)CreateNewTank(1);
			if(remotedead)CreateNewTank(2);
			
		}
		//NextMove for player tanks
		for(PlayerTank player:AlivePlayerTanks) {
			player.NextMove(this);
		}
		//NextMove for shells
		for(CannonShell shells:AliveShells) {
			shells.NextMove(this);
		}
		//NextMove for Ai tanks
		//Create new tank if all of them are dead
		if(SingleOrMulti == false) {	
			//If all tanks are destroyed
			if(AliveAiTanks.isEmpty())
				CreateNewTank(0);
		}
		//Nextmove
		for(AiTank tank:AliveAiTanks) {
			tank.NextMove(this);
		}
		//NextMove for explosions
		for(Explosion boom:ActiveExplosions) {
			boom.NextMove();
		}
		//Clear dead objects
		DestroyDeadThings();
		//Game over conditions
		
	}
	
	/**
	 * Creates new tank
	 * @param Type 0-AiTank 1-Player 2-RemotePlayer
	 */
	private void CreateNewTank(int Type) {
		int[] pos = GameLogicUtility.RandomPositionGen(MapGridArray);
		if(Type == 1) {			
			AlivePlayerTanks.add(new PlayerTank(pos[0], pos[1], true));
		}
		if(Type == 2) {
			AlivePlayerTanks.add(new PlayerTank(pos[0], pos[1], false));
		}
		if(Type == 0) {
			AliveAiTanks.add(new AiTank(pos[0], pos[1], Difficulty));
		}
	}
		
	/**
	 * Removes dead objects from active objects list
	 */
	private void DestroyDeadThings() {
		int i = 0;
		//AI tanks
		i = AliveAiTanks.size();
		while(i != 0) {
			if(AliveAiTanks.get(i-1).IsAlive == false) {
				AliveAiTanks.remove(i-1);
			}
			i--;
		}
		//Shells
		i = AliveShells.size();
		while(i != 0) {
			if(AliveShells.get(i-1).IsAlive == false) {
				AliveShells.remove(i-1);
			}
			i--;
		}
		//Explosions		
		i = ActiveExplosions.size();
		while(i != 0) {
			if(ActiveExplosions.get(i-1).IsAlive == false) {
				ActiveExplosions.remove(i-1);
			}
			i--;
		}
		//Player tanks
		i = AlivePlayerTanks.size();
		while(i != 0) {
			if(AlivePlayerTanks.get(i-1).IsAlive == false) {
				AlivePlayerTanks.remove(i-1);
			}
			i--;
		}
	}
	
	/**
	 * Starts game, creates thread for gamelogic
	 */
	public void StartGame() {
		//Do some shit, initialize game
		CreateNewTank(1);	//Create player
		//Start thread
		if(GameLogicThread == null) {
			System.out.println("Starting game");
			GameLogicThread = new Thread(this,"GAMELOGIC");
			GameLogicThread.start();
		}
		//Terminate
		
	}
	
	/**
	 * Calls thread to redraw gamefield
	 */
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
			while(GameOver != true) {
				UpdateFrame();
				callFrameUpdate();
				//Wait
				Thread.sleep(FrameTime);
				TimeElapsed += 1 / (double)FrameTime;
			}
			System.out.println("Game over man, it's game over");
			
		} catch (InterruptedException e) {
			//Fuck yo exceptions
		}

	}
}