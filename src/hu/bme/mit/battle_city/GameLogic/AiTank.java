package hu.bme.mit.battle_city.GameLogic;

public class AiTank extends BaseTank{
	private int DifficultyModifier;
	
	public void NextMove(GameWorld gameworld) {
		//Stand still TODO
	}
	
	private boolean ChackLineOfSight(GameWorld gameworld) {
		//Return line of sight		
		
		return false;
	}
	
	
	public AiTank(int x,int y,int difficulty) {
		IsAlive = true;
		GridLocX = x;
		GridLocY = y;
		Heading = 0;
		Speed = 3;
		CoolDown = 0;
		Health = 1;
	}
}
