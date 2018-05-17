package hu.bme.mit.battle_city.GameLogic;

public class BaseTank extends MovingObject{
	private int Health;
	
	private void Die() {
		if(Heading == 0)
			IsAlive = false;
	}
}
