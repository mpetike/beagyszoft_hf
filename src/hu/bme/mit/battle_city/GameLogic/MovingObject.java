package hu.bme.mit.battle_city.GameLogic;

/**
 * Base class for all dynamic elements on the playfield
 * @author Peti
 *
 */
public class MovingObject{
	public int GridLocX;
	public int GridLocY;
	public int Heading;
	protected double CoolDown;
	protected double Speed;
	public boolean IsAlive;
	
	/**
	 * Moves the object forward, doesn't check for collision
	 */
	protected void MoveForward() {
		if(CoolDown > 0) {			
			return;
		}		
		CoolDown += 30;
		//Move object
		if(Heading == 0)
			GridLocY--;
		else if(Heading == 1)
			GridLocX++;
		else if(Heading == 2)
			GridLocY++;
		else if(Heading == 3)
			GridLocX--;
	}
}
