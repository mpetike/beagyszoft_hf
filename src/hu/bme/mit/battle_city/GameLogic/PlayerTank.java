package hu.bme.mit.battle_city.GameLogic;


public class PlayerTank extends BaseTank{
	public boolean LocalOrRemote;
	
	/**
	 * 
	 * @param x - GridLocX starting position
	 * @param y - GridLocY starting position
	 * @param LocRemote false - local player 
	 */
	public PlayerTank(int x,int y,boolean LocRemote) {
		LocalOrRemote = LocRemote;
		GridLocX = x;
		GridLocY = y;
		Health = 3;
		Speed = 2;
		Heading = 0; //TODO: random heading???
		IsAlive = true;
		CoolDown = 0;
	}
	
	
	public void NextMove(GameWorld gameworld) {
		int input = EvaulateInputs();
		
		if(CheckForwardCollision(gameworld) == true)	//TODO
			MoveForward();		
		return;
	}
	
	/**
	 * 
	 * @return 0-nothing 1-moveforward 2-shoot 3-rot clockwise 4-rot c clockwise
	 */
	private int EvaulateInputs() {
		return 0;
	}
	
}
