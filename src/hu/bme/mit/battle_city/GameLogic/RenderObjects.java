package hu.bme.mit.battle_city.GameLogic;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class holding objects needed for rendering the game field, sent to client in multiplayer mode 
 * @author Peti
 *
 */
public class RenderObjects implements Serializable{
	
	/**
	 * wat
	 */
	private static final long serialVersionUID = 1L;
	public boolean[][] MapGridArray;
	public ArrayList<PlayerTank> AlivePlayerTanks;
	public ArrayList<AiTank> AliveAiTanks;
	public ArrayList<CannonShell> AliveShells;
	public ArrayList<Explosion> ActiveExplosions;
	public boolean GameOver;
	
	/**
	 * 
	 * @param gameworld
	 */
	public RenderObjects(GameWorld gameworld) {
		MapGridArray = gameworld.MapGridArray;
		AliveAiTanks = gameworld.AliveAiTanks;
		AlivePlayerTanks = gameworld.AlivePlayerTanks;
		AliveShells = gameworld.AliveShells;
		ActiveExplosions = gameworld.ActiveExplosions;
		GameOver = gameworld.GameOver;
	}

}
