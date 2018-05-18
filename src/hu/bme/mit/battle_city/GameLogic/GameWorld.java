package hu.bme.mit.battle_city.GameLogic;

public class GameWorld {
	public boolean[][] MapGridArray;
	public boolean SingleOrMulti;
	private int TimeElapsed;
	
	public GameWorld(String map_path, boolean sp_mode) {
		SingleOrMulti = sp_mode;
		MapGridArray = GameLogicUtility.LoadMapFromFile(map_path);
	}
	
	public void StartGame() {
		//Do some shit
	}
	
}
