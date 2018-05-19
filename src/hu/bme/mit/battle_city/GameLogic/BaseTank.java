package hu.bme.mit.battle_city.GameLogic;

public class BaseTank extends MovingObject{
	protected int Health;
	
	private void Die() {
		if(Heading == 0)
			IsAlive = false;
	}
	
	/**
	 * 
	 * @param gameworld Gameworld object, needed for map and enemy location
	 * @return true - clear, false - collision
	 */
	public boolean CheckForwardCollision(GameWorld gameworld) {
		//Calculate forward position
		int x_next = GridLocX,y_next = GridLocY;
		switch(Heading) {
			case 0 : y_next--;break;
			case 1 : x_next++;break;
			case 2 : y_next++;break;
			case 3 : x_next--;break;
		}
		//Check if out of bounds
		if((y_next < 0) || (y_next > 14) || (x_next < 0) || (x_next > 14))return false;
		//Check if collision with walls
		if(gameworld.MapGridArray[y_next][x_next] == true)return false;
		//Check for tanks TODO
		//AI tank
		//Player tanks
		
		return true;
	}
	
	/**
	 * Creates a new shell in the gameworld
	 * @param gameworld Gameworld object
	 */
	private void FireShell(GameWorld gameworld) {
		gameworld.AliveShells.add(new CannonShell(GridLocX, GridLocX, Heading));
	}
	
	public void GetDamaged() {
		Health = Health - 1;
		Die();
	}
	
	/**
	 * Changes tank heading
	 * @param LeftRight 1-left 2-right
	 */
	protected void rotate(int LeftRight) {
		int newheading = Heading;
		if(LeftRight == 2) {
			newheading = (newheading + 1) % 4;
		}
		if(LeftRight == 1) {
			newheading--;
			if(newheading == -1)
				newheading = 3;
		}		
		Heading = newheading;
	}
}
