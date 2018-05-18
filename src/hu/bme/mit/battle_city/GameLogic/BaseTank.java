package hu.bme.mit.battle_city.GameLogic;

public class BaseTank extends MovingObject{
	private int Health;
	
	private void Die() {
		if(Heading == 0)
			IsAlive = false;
	}
	
	public void GetDamage() {
		Health = Health - 1;
	}
	
	private void rotate(int LeftRight) {
		if(LeftRight == )
	}
}
