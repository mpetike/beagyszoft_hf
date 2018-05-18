package hu.bme.mit.battle_city.GameLogic;

public class MovingObject {
	public int GridLocX;
	public int GridLocY;
	public int Heading;
	private int CoolDown;
	private int Speed;
	public boolean IsAlive;
	
	private void MoveForward() {
		if(Heading == 0)
			GridLocY--;
		else if(Heading == 1)
			GridLocX++;
		else if(Heading == 2)
			GridLocY = GridLocY++;
		else if(Heading == 3)
			GridLocX--;
	}
}
