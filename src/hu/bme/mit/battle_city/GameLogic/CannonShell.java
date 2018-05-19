package hu.bme.mit.battle_city.GameLogic;

public class CannonShell extends MovingObject{
	
	public CannonShell(int x,int y,int head) {
		IsAlive = true;
		CoolDown = 0;
		Heading = head;
		GridLocX = x;
		GridLocY = y;
	}
	
	private void Explode(GameWorld gameworld) {
		IsAlive = false;
		gameworld.ActiveExplosions.add(new Explosion(GridLocX, GridLocX));
	}
	
	/**
	 * 
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
	
	public void NextMove(GameWorld gameworld) {
		if(IsAlive == false)return;
		//Move forward (if we can)
		if(CoolDown > 0) {
			CoolDown--;
		}
		else {
			CoolDown = 10;
			MoveForward();
		}
		//Check if hitting a wall
		CheckOnWorldCollision(gameworld);
		CheckOnTankCollision(gameworld);
	}
}
