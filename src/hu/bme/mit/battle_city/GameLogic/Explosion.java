package hu.bme.mit.battle_city.GameLogic;

/**
 * Simple class for explosion sprites on maps, created by dying canonshells
 * @author Peti
 *
 */
public class Explosion extends MovingObject{
	
	public Explosion(int x,int y) {
		IsAlive = true;
		CoolDown = 45;
		GridLocX = x;
		GridLocY = y;
	}
	
	/**
	 * Countdown for the sprite
	 */
	private void CountDown() {
		CoolDown--;
	}
	
	/**
	 * Explosion next move, counts down, when reaches zero disappears
	 */
	public void NextMove() {
		CountDown();
		if(CoolDown <= 0)
			IsAlive = false;
	}
	
}
