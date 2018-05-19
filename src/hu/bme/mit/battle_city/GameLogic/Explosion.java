package hu.bme.mit.battle_city.GameLogic;

public class Explosion extends MovingObject{
	
	public Explosion(int x,int y) {
		IsAlive = true;
		CoolDown = 45;
		GridLocX = x;
		GridLocY = y;
	}
	
	
	private void CountDown() {
		CoolDown--;
	}
	
	/**
	 * Explosion next move, counts down, when reaches zero dissapears
	 */
	public void NextMove() {
		CountDown();
		if(CoolDown <= 0)
			IsAlive = false;
	}
	
}
