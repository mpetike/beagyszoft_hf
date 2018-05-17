package hu.bme.mit.battle_city.GameLogic;

public class GameLogicDebug {
	
	public static void main(String [] args) {
		System.out.println("Ayy lmao Debugclass");
		
		GameWorld gameworld = new GameWorld();
		
		gameworld.MapGridArray = MapLoadUtility.LoadMapFromFile("C:\\Users\\Peti\\Documents\\VIK_MSc\\szoftech\\beagyszoft_hf\\src\\hu\\bme\\mit\\battle_city\\GameLogic\\map_default.txt");
		
		System.out.println("Map loaded");
		
	}
}
