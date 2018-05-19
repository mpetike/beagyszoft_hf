package hu.bme.mit.battle_city.GameLogic;

public class BaseTank extends MovingObject{
	protected int Health;
	
	private void Die() {
		if(Heading == 0)
			IsAlive = false;
	}
	
	public void GetDamage() {
		Health = Health - 1;
	}
	
	protected void rotate(int LeftRight) {
	}
}
