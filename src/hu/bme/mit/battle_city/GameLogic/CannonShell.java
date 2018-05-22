package hu.bme.mit.battle_city.GameLogic;

/**
 * Canonshell fired by tanks, causes damage to tanks hit, no friend or foe distinction
 * @author Peti
 *
 */
public class CannonShell extends MovingObject{
	
	public CannonShell(int x,int y,int head) {
		IsAlive = true;
		CoolDown = 0;
		Speed = 15;	//Gotta go fast
		Heading = head;
		GridLocX = x;
		GridLocY = y;
	}
	
	/**
	 * Creates explosion and dies
	 * @param gameworld
	 */
	private void Explode(GameWorld gameworld) {
		IsAlive = false;
		gameworld.ActiveExplosions.add(new Explosion(GridLocX, GridLocY));
	}
	
	/**
	 * Checks collision with gameworld
	 * @param gameworld Gameworld object
	 * @return true - colliding world false - no collision
	 */
	private void CheckOnWorldCollision(GameWorld gameworld) {
		if(gameworld.MapGridArray[GridLocY][GridLocX] == true) {
			Explode(gameworld);
		}
	}
	
	/**
	 * Check whether it's hitting a tank, if it does it causes damage and explodes
	 * @param gameworld GameWorld
	 * @return true if a tank has been hit
	 */
	private void CheckOnTankCollision(GameWorld gameworld) {
		//Ai tanks
		for(AiTank tank:gameworld.AliveAiTanks) {
			if((tank.GridLocX == GridLocX) && (tank.GridLocY == GridLocY)) {
				tank.GetDamaged();
				Explode(gameworld);
				return;
			}
		}
		//Player tanks
		for(PlayerTank tank:gameworld.AlivePlayerTanks) {
			if((tank.GridLocX == GridLocX) && (tank.GridLocY == GridLocY)) {
				tank.GetDamaged();
				Explode(gameworld);
				return;
			}
		}
	}
	
	/**
	 * Canonshell nextmove, moves in straight line until it hits something
	 * @param gameworld
	 */
	public void NextMove(GameWorld gameworld) {
		if(IsAlive == false)return;
		//Cooldown dec
		if(CoolDown > 0)
			CoolDown = CoolDown - Speed;
		//Move forward (if we can)
		MoveForward();
		//Check if hitting a wall
		CheckOnWorldCollision(gameworld);
		CheckOnTankCollision(gameworld);
	}
}
