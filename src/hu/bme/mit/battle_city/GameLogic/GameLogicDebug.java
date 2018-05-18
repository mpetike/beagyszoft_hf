package hu.bme.mit.battle_city.GameLogic;

public class GameLogicDebug {
	
	public static void main(String [] args) {
		
		System.out.println("Ayy lmao Debugclass");
		
		GameWorld gameworld = new GameWorld("mapfiles/map_default.txt",true);
		
		gameworld.StartGame();
		
		System.out.println("Map loaded");
		
	}
}
