package hu.bme.mit.battle_city.GameLogic;

public class AiTank extends BaseTank{
	
	
	public void NextMove(GameWorld gameworld) {
		
	}
	
	private boolean ChackLineOfSight(GameWorld gameworld) {
		//Return line of sight		
		
		return false;
	}
	
	
	public AiTank(int x,int y) {
		GridLocX = x;
		GridLocY = y;
	}
}
