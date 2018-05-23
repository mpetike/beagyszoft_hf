package hu.bme.mit.battle_city.GameLogic;

import java.util.Queue;

/**
 * Tank controlled by a player, can be a local player or a remote one
 * @author Peti
 *
 */
public class PlayerTank extends BaseTank{
	public boolean LocalOrRemote;
	
	/**
	 * 
	 * @param x - GridLocX starting position
	 * @param y - GridLocY starting position
	 * @param LocRemote true - local player 
	 */
	public PlayerTank(int y,int x,boolean LocRemote) {
		LocalOrRemote = LocRemote;
		GridLocX = x;
		GridLocY = y;
		Health = 3;
		Speed = 3;	//1/3 sec for movement
		Heading = 0; 
		IsAlive = true;
		CoolDown = 0;
	}
	
	/**
	 * NextMoce for player controlled tanks, evaluates inputs from set source and executes them
	 * @param gameworld
	 */
	public void NextMove(GameWorld gameworld) {
		int input_move;
		//Movement cooldown
		if(CoolDown > 0)
			CoolDown = CoolDown - Speed;	
		//Get inputs for proper player
		if(LocalOrRemote)
			input_move = EvaulateInputs(gameworld.LocalPlayerQueue);
		else
			input_move = EvaulateInputs(gameworld.RemotePlayerQueue);
		//Movements
		switch(input_move){
			case 0 : break;
			case 3 : {
				if(CheckForwardCollision(gameworld))
					MoveForward();
				} break;
			case 1 : Rotate(2);break;
			case 2 : Rotate(1);break;
			case 5 : FireShell(gameworld); break;
		}
		return;
	}
	
	/**
	 * Gets latest input command from an input queue
	 * @return 0-nothing 1-moveforward 2-shoot 3-rot clockwise 4-rot c clockwise
	 */
	private int EvaulateInputs(Queue<Integer> input) {
		if(input.isEmpty()) return 0;
		int inpt_key = 0;
		while(!input.isEmpty()) {
			inpt_key = input.poll();
		}
		return inpt_key + 1;
	}
	
}
