package hu.bme.mit.battle_city.GameLogic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {
	
	public PathFinder() {
		
	}
	
	/**
	 * Pathfinds shortest route to target, returns direction in witch target should move
	 * @param self_pos	Self position [0]-y [1]-x
	 * @param target_pos Targets position [0]-y [1]-x
	 * @param map Map held in a boolean array - map[y][x]
	 * @return Movement direction 0 - up 1 - right 2 - down 3 - left 4 - no_path
	 */
	static public int PathFindToTarget(int selfpos_y,int selfpos_x,int targetpos_y,int targetpos_x,boolean[][] map) {
		//Array holding walls and already visited areas
		//Fuck dis
		boolean[][] pathfind_map = new boolean[15][15];
		int y,x;
		for(y = 0;y != 15;y ++) {
			for(x = 0; x != 15;x++) {
				pathfind_map[y][x] = map[y][x];
			}
		}
		//Arraylist holding path to target
		ArrayList<int[]> path2target = null;
		boolean path2target_found = false;
		//Queue holding places to be visited
		Queue<PathNode> node_queue = new LinkedList<>();
		
		//Place starting point in queue
		node_queue.add(new PathNode(selfpos_y, selfpos_x, null));
		//And mark it as invalid on searchmap		
		pathfind_map[selfpos_y][selfpos_x] = true;
		
		
		while(!path2target_found) {
			//Break if no path to target available
			if(node_queue.isEmpty())return 4;
			
			//Get next node
			PathNode currentnode = node_queue.poll();
			//Check if current node is on the target
			if((currentnode.y == targetpos_y) && (currentnode.x == targetpos_x)) {
				path2target = currentnode.Path;
				path2target_found = true;
			}
			//Check up
			if(pathfind_map[currentnode.y - 1][currentnode.x] == false) {
				node_queue.add(new PathNode(currentnode.y - 1, currentnode.x, currentnode));
				pathfind_map[currentnode.y - 1][currentnode.x] = true;
			}
			//Check right
			if(pathfind_map[currentnode.y ][currentnode.x + 1] == false) {
				node_queue.add(new PathNode(currentnode.y, currentnode.x + 1, currentnode));
				pathfind_map[currentnode.y ][currentnode.x + 1] = true;
			}
			//Check down
			if(pathfind_map[currentnode.y + 1][currentnode.x] == false) {
				node_queue.add(new PathNode(currentnode.y + 1, currentnode.x, currentnode));
				pathfind_map[currentnode.y + 1][currentnode.x] = true;
			}
			//Check left
			if(pathfind_map[currentnode.y ][currentnode.x - 1] == false) {
				node_queue.add(new PathNode(currentnode.y, currentnode.x - 1, currentnode));
				pathfind_map[currentnode.y ][currentnode.x - 1] = true;
			}
		}		
		//Get direction from path
		int mov_dir = 4;
		int next_y = path2target.get(1)[0];
		int next_x = path2target.get(1)[1];
		if((selfpos_y - 1 == next_y) && (selfpos_x == next_x))mov_dir = 0;
		if((selfpos_y == next_y) && (selfpos_x + 1 == next_x))mov_dir = 1;
		if((selfpos_y + 1 == next_y) && (selfpos_x == next_x))mov_dir = 2;
		if((selfpos_y == next_y) && (selfpos_x - 1 == next_x))mov_dir = 3;
		
		return mov_dir;
	}
	
	
}
