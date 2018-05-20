package hu.bme.mit.battle_city.GameLogic;

public class AiTank extends BaseTank{
	private int DifficultyModifier;
	
	public void NextMove(GameWorld gameworld) {
		if(IsAlive == false)return;
		//Cooldown dec
		if(CoolDown > 0)
			CoolDown = CoolDown - Speed;
		//Check line of sight, if clear view: fire
		if(CheckLineOfSight(gameworld)) {
			FireShell(gameworld);
		}
		else
			PathFindToTarget(gameworld);
	}
	
	private boolean CheckLineOfSight(GameWorld gameworld) {
		//Return line of sight		
		
		return false;
	}
	
	private void PathFindToTarget(GameWorld gameworld) {
		//Only if there is a player on the field
		if(!gameworld.AlivePlayerTanks.isEmpty()) {
			if(!gameworld.AlivePlayerTanks.isEmpty()) {	
				int player_x, player_y;
				player_x = gameworld.AlivePlayerTanks.get(0).GridLocX;
				player_y = gameworld.AlivePlayerTanks.get(0).GridLocY;
				//Print movement direction
				int dir2go = PathFinder.PathFindToTarget(GridLocY, GridLocX, player_y, player_x, gameworld.MapGridArray);
				if(dir2go == Heading)
					MoveForward();
				if(dir2go != 4)
					Rotate(1);
			}	
		}
	}
	
	public AiTank(int x,int y,int difficulty) {
		IsAlive = true;
		GridLocX = x;
		GridLocY = y;
		Heading = 0;
		Speed = 3;
		CoolDown = 0;
		DifficultyModifier = difficulty;
		Health = 1;	//TODO: health/difficulty
	}
}
