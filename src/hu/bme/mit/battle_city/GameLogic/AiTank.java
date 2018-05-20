package hu.bme.mit.battle_city.GameLogic;

public class AiTank extends BaseTank{
	private int DifficultyModifier;
	private int Hesitation;
	private int HesitationReset;
	
	public void NextMove(GameWorld gameworld) {
		if(IsAlive == false)return;
		//Cooldown dec
		if(CoolDown > 0)
			CoolDown = CoolDown - Speed;
		//Check line of sight, if clear view: fire
		if(CheckLineOfSight(gameworld)) {
			if(Hesitation > 0) {
				Hesitation -= Speed;
			}
			else {
				FireShell(gameworld);
				Hesitation = HesitationReset;
			}
		}
		else {
			PathFindToTarget(gameworld);
			Hesitation = HesitationReset;
		}
	}
	
	private boolean CheckLineOfSight(GameWorld gameworld) {
		if(!gameworld.AlivePlayerTanks.isEmpty()) {
			int player_x = gameworld.AlivePlayerTanks.get(0).GridLocX;
			int player_y = gameworld.AlivePlayerTanks.get(0).GridLocY;
			int i;
			if(Heading == 0) {
				for(i=0;gameworld.MapGridArray[GridLocY - i][GridLocX] == false;i++) {
					if(((GridLocY - i) == player_y) && ((GridLocX) == player_x))
						return true;
				}
			}
			else if(Heading == 1) {
				for(i=0;gameworld.MapGridArray[GridLocY][GridLocX + i] == false;i++) {
					if(((GridLocY) == player_y) && ((GridLocX + i) == player_x))
						return true;
				}				
			}
			else if(Heading == 2) {
				for(i=0;gameworld.MapGridArray[GridLocY + i][GridLocX] == false;i++) {
					if(((GridLocY + i) == player_y) && ((GridLocX) == player_x))
						return true;
				}	
			}
			else if(Heading == 3) {
				for(i=0;gameworld.MapGridArray[GridLocY][GridLocX - i] == false;i++) {
					if(((GridLocY) == player_y) && ((GridLocX - i) == player_x))
						return true;
				}				
			}
		}
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
				if(dir2go == Heading) {
					if(CheckForwardCollision(gameworld)) {
						MoveForward();
					}
				}
				else if(dir2go != 4) {
					Rotate(1);
				}
				else if(dir2go == 4)
					GetDamaged();

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
		HesitationReset = 50; //TODO difficulty
		Hesitation = 0; 
	}
}
