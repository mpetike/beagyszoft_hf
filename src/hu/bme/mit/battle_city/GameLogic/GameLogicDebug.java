package hu.bme.mit.battle_city.GameLogic;

public class GameLogicDebug {
	
	public static void main(String [] args) {
		
		System.out.println("Ayy lmao Debugclass");
		
		//GameWorld gameworld = new GameWorld("mapfiles/map_default.txt",true,0,false,false);
		
		//Run thread
		//gameworld.StartGame();
		
		
		//Sleep
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("Game execution done");
		
	}
}
